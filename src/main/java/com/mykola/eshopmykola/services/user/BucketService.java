package com.mykola.eshopmykola.services.user;

import com.mykola.eshopmykola.dtos.order.OrderDTO;
import com.mykola.eshopmykola.dtos.user.BucketDTO;
import com.mykola.eshopmykola.dtos.user.BucketProductDTO;
import com.mykola.eshopmykola.models.order.Order;
import com.mykola.eshopmykola.models.order.OrderStatus;
import com.mykola.eshopmykola.models.order.OrderToProduct;
import com.mykola.eshopmykola.models.product.Product;
import com.mykola.eshopmykola.models.user.Bucket;
import com.mykola.eshopmykola.models.user.User;
import com.mykola.eshopmykola.repositories.order.OrderRepository;
import com.mykola.eshopmykola.repositories.order.OrderToProductRepository;
import com.mykola.eshopmykola.repositories.product.ProductRepository;
import com.mykola.eshopmykola.repositories.user.BucketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BucketService {

	private final BucketRepository bucketRepository;
	private final ProductRepository productRepository;
	private final UserService userService;
	private final OrderRepository orderRepository;
	private final OrderToProductRepository orderToProductRepository;

	@Autowired
	public BucketService(BucketRepository bucketRepository, ProductRepository productRepository, UserService userService, OrderRepository orderRepository, OrderToProductRepository orderToProductRepository) {
		this.bucketRepository = bucketRepository;
		this.productRepository = productRepository;
		this.userService = userService;
		this.orderRepository = orderRepository;
		this.orderToProductRepository = orderToProductRepository;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	public Bucket createBucket(User user, Long productId) {
		Bucket bucket = new Bucket();
		bucket.setBucketOwner(user);

		List<Product> productList = new ArrayList<>();
		productList.add(productRepository.getOne(productId));

		bucket.setProducts(productList);
		return bucketRepository.save(bucket);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	public void addProducts(Bucket bucket, Long productId) {
		List<Product> products = bucket.getProducts();
		List<Product> newProductList;
		if (products == null) {
			newProductList = new ArrayList<>();
		} else {
			newProductList = new ArrayList<>(products);
		}
		newProductList.add(productRepository.getOne(productId));
		bucket.setProducts(newProductList);
		bucketRepository.save(bucket);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	public void deleteOneBucketProductDTO(Long id, String uuid) {
		User user = userService.getByUuid(uuid);
		List<Product> products = user.getBucket().getProducts();

		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId().equals(id)) {
				products.remove(i);
				break;
			}
		}

		user.getBucket().setProducts(products);
		userService.save(user);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	public void deleteRowBucketProductDTO(Long id, String uuid) {
		User user = userService.getByUuid(uuid);
		List<Product> products = user.getBucket().getProducts();

		List<Product> productsToDelete = new ArrayList<>();
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId().equals(id)) {
				productsToDelete.add(products.get(i));
			}
		}
		products.removeAll(productsToDelete);

		user.getBucket().setProducts(products);
		userService.save(user);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	public void deleteAllBucketProductDTO(String uuid) {
		User user = userService.getByUuid(uuid);
		user.getBucket().setProducts(new ArrayList<>());
		userService.save(user);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	public BucketDTO getBucketByUuid(String uuid) {
		User user = userService.getByUuid(uuid);


		if (user.getBucket() == null) {
			return new BucketDTO();
		}


		BucketDTO bucketDto = new BucketDTO();
		Map<Long, BucketProductDTO> mapByProductId = new HashMap<>();


		List<Product> products = user.getBucket().getProducts();

		for (Product product : products) {

			BucketProductDTO detail = mapByProductId.get(product.getId());

			if (detail == null) {
				mapByProductId.put(product.getId(), new BucketProductDTO(product));
			} else {
				detail.setAmount(detail.getAmount() + 1);
				detail.setSum(detail.getSum() + product.getPrice());
			}
		}

		bucketDto.setBucketProductDTOs(new ArrayList<>(mapByProductId.values()));
		bucketDto.aggregate();

		return bucketDto;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	public void commitBucketToOrder(String uuid, OrderDTO orderDTO) {
		User user = userService.getByUuid(uuid);
		BucketDTO bucketDTO = getBucketByUuid(uuid);
		Bucket bucket = user.getBucket();

		//хз чи надо
		if (bucket == null || bucket.getProducts().isEmpty()) {
			//todo корзина пуста, ви нічого не заказали;
			return;
		}

		if (user.getEmail() == null || user.getEmail().isBlank()) {
			user.setEmail(orderDTO.getEmail());
		}
		if (user.getFirstName() == null || user.getFirstName().isBlank()) {
			user.setFirstName(orderDTO.getFirstName());
		}
		if (user.getLastName() == null || user.getLastName().isBlank()) {
			user.setLastName(orderDTO.getLastName());
		}


		Order order = new Order();

		order.setAddress(orderDTO.getAddress());
		order.setPhone_number(orderDTO.getPhone_number());
		order.setPaymentMethod(orderDTO.getPaymentMethod());
		order.setOrderOwner(user);
		order.setStatus(OrderStatus.NEW);
		order.setSum(bucketDTO.getSum());





		List<OrderToProduct> ordersToProduct = new ArrayList<>();

		for (BucketProductDTO bucketProductDTO : bucketDTO.getBucketProductDTOs()) {

			OrderToProduct orderToProduct = new OrderToProduct();

			orderToProduct.setProduct(productRepository.getById(bucketProductDTO.getProductId()));
			orderToProduct.setAmount(bucketProductDTO.getAmount());
			orderToProduct.setPrice(bucketProductDTO.getPrice());
			orderToProduct.setSum(bucketProductDTO.getSum());
			orderToProduct.setOrder(order);

			orderToProductRepository.save(orderToProduct);
			ordersToProduct.add(orderToProduct);
		}


		order.setOrderedProducts(ordersToProduct);







		orderRepository.save(order);
		bucket.getProducts().clear();
		bucketRepository.save(bucket);
	}
}
