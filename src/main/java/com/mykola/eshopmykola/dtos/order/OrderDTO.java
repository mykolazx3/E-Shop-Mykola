package com.mykola.eshopmykola.dtos.order;

import com.mykola.eshopmykola.models.order.OrderToProduct;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String phone_number;
	private String paymentMethod;
	private LocalDateTime created;
	private String status;
	private List<OrderToProduct> ordersToProduct;
	private double sum;

	public OrderDTO() {
	}

	public OrderDTO(long id, String firstName, String lastName, String email, String address, String phone_number, String paymentMethod, LocalDateTime created, String status, List<OrderToProduct> ordersToProduct, double sum) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.phone_number = phone_number;
		this.paymentMethod = paymentMethod;
		this.created = created;
		this.status = status;
		this.ordersToProduct = ordersToProduct;
		this.sum = sum;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderToProduct> getOrdersToProduct() {
		return ordersToProduct;
	}

	public void setOrdersToProduct(List<OrderToProduct> ordersToProduct) {
		this.ordersToProduct = ordersToProduct;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
}


