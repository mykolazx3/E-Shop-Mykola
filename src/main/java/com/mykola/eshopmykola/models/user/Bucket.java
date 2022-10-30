package com.mykola.eshopmykola.models.user;

import com.mykola.eshopmykola.models.product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "buckets")
public class Bucket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@OneToOne
	@JoinColumn(name = "bucket_owner_id", referencedColumnName = "id")
	private User bucketOwner;


	@ManyToMany
	@JoinTable(name = "buckets_products",
			joinColumns = @JoinColumn(name = "bucket_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products;


	public Bucket() {
	}

	public Bucket(User bucketOwner, List<Product> products) {
		this.bucketOwner = bucketOwner;
		this.products = products;
	}

	public Bucket(User bucketOwner) {
		this.bucketOwner = bucketOwner;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getBucketOwner() {
		return bucketOwner;
	}

	public void setBucketOwner(User bucketOwner) {
		this.bucketOwner = bucketOwner;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
