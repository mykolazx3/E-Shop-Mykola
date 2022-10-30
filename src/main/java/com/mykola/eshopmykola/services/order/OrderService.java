package com.mykola.eshopmykola.services.order;

import com.mykola.eshopmykola.dtos.order.OrderDTO;
import com.mykola.eshopmykola.models.order.Order;
import com.mykola.eshopmykola.repositories.order.OrderRepository;
import com.mykola.eshopmykola.repositories.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final UserRepository userRepository;

	public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public List<OrderDTO> getAllMyOrderDTOs(String uuid) {

		List<Order> orders = orderRepository.findAllByOrderOwner(userRepository.findFirstByUuid(uuid));
		List<OrderDTO> orderDTOs = new ArrayList<>();

		for(Order order: orders){
			OrderDTO orderDTO = new OrderDTO();

			orderDTO.setId(order.getId());
			orderDTO.setCreated(order.getCreated());
			orderDTO.setStatus(order.getStatus().name());

			orderDTOs.add(orderDTO);
		}

		return orderDTOs;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public List<OrderDTO> getAllOrderDTOs() {
		List<Order> orders = orderRepository.findAll();
		List<OrderDTO> orderDTOs = new ArrayList<>();

		for(Order order: orders){
			OrderDTO orderDTO = new OrderDTO();

			orderDTO.setId(order.getId());
			orderDTO.setCreated(order.getCreated());
			orderDTO.setStatus(order.getStatus().name());

			orderDTOs.add(orderDTO);
		}

		return orderDTOs;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public OrderDTO getOrderDetails(long id){
		Optional<Order> orderOpt = orderRepository.findById(id);
		Order order = orderOpt.get();

		OrderDTO orderDTO = new OrderDTO();

		orderDTO.setId(order.getId());
		orderDTO.setFirstName(order.getOrderOwner().getFirstName());
		orderDTO.setLastName(order.getOrderOwner().getLastName());
		orderDTO.setEmail(order.getOrderOwner().getEmail());
		orderDTO.setAddress(order.getAddress());
		orderDTO.setPhone_number(order.getPhone_number());
		orderDTO.setPaymentMethod(order.getPaymentMethod());
		orderDTO.setCreated(order.getCreated());
		orderDTO.setStatus(order.getStatus().name());
		orderDTO.setOrdersToProduct(order.getOrderedProducts());
		orderDTO.setSum(order.getSum());

		return orderDTO;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
