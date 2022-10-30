package com.mykola.eshopmykola.models.product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_category")
public class ProductCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "title")
	private String title;

	@ManyToMany
	@JoinTable(name = "products_categories",
			joinColumns = @JoinColumn(name = "product_category_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products;

	public ProductCategory() {
	}

	public ProductCategory(String title, List<Product> products) {
		this.title = title;
		this.products = products;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
