package com.mykola.eshopmykola.models.order;

import com.mykola.eshopmykola.models.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "orders_to_product")
public class OrderToProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "amount")
	private int amount;

	@Column(name = "price")
	private double price;

	@Column(name = "sum")
	private double sum;

	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;

	public OrderToProduct() {
	}

	public OrderToProduct(int amount, double price, double sum, Order order, Product product) {
		this.amount = amount;
		this.price = price;
		this.sum = sum;
		this.order = order;
		this.product = product;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


}
