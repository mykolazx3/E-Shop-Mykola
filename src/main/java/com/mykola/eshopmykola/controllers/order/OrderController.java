package com.mykola.eshopmykola.controllers.order;

import com.mykola.eshopmykola.dtos.order.OrderDTO;
import com.mykola.eshopmykola.services.order.OrderService;
import com.mykola.eshopmykola.services.user.BucketService;
import com.mykola.eshopmykola.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrderController {
	private final OrderService orderService;
	private final UserService userService;
	private final BucketService bucketService;

	@Autowired
	public OrderController(OrderService orderService, UserService userService, BucketService bucketService) {
		this.orderService = orderService;
		this.userService = userService;
		this.bucketService = bucketService;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping
	public String ordersList(Model model, Principal principal, HttpSession httpSession){
		model.addAttribute("orderDTOs", orderService.getAllMyOrderDTOs(userService.getPrincipalOrAnonymousUuid(principal, httpSession)));
		return "orders";
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/manager")
	public String ordersManager(Model model){
		model.addAttribute("orderDTOs", orderService.getAllOrderDTOs());
		return "orders-manager";
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/{id}/details")
	public String orderDetails(@PathVariable("id") long id, Model model){
		model.addAttribute("orderDTO", orderService.getOrderDetails(id));
		return "order-details";
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@PostMapping("/create-order")
	public String createOrder(Principal principal, HttpSession httpSession, OrderDTO orderDTO) {

		bucketService.commitBucketToOrder(userService.getPrincipalOrAnonymousUuid(principal, httpSession), orderDTO);

		return "redirect:/bucket";
	}
}
