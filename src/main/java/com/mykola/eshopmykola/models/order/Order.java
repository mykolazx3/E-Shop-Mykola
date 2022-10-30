package com.mykola.eshopmykola.models.order;

import com.mykola.eshopmykola.models.user.User;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "sum")
	private double sum;

	@Column(name = "address")
	private String address;

	@Column(name = "phone_number")
	private String phone_number;

	@Column(name = "payment_method")
	private String paymentMethod;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@Column(name = "created")
	@CreationTimestamp
	private LocalDateTime created;

	@Column(name = "updated")
	@UpdateTimestamp
	private LocalDateTime updated;

	@ManyToOne
	@JoinColumn(name = "order_owner_id", referencedColumnName = "id")
	private User orderOwner;

	@OneToMany(mappedBy = "order")
	@Cascade({org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private List<OrderToProduct> ordersToProduct;

	public Order() {
	}

	public Order(double sum, String address, String phone_number, String paymentMethod, OrderStatus status, LocalDateTime created, LocalDateTime updated, User orderOwner, List<OrderToProduct> ordersToProduct) {
		this.sum = sum;
		this.address = address;
		this.phone_number = phone_number;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.created = created;
		this.updated = updated;
		this.orderOwner = orderOwner;
		this.ordersToProduct = ordersToProduct;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
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

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public User getOrderOwner() {
		return orderOwner;
	}

	public void setOrderOwner(User orderOwner) {
		this.orderOwner = orderOwner;
	}

	public List<OrderToProduct> getOrderedProducts() {
		return ordersToProduct;
	}

	public void setOrderedProducts(List<OrderToProduct> orderToProducts) {
		this.ordersToProduct = orderToProducts;
	}
}
