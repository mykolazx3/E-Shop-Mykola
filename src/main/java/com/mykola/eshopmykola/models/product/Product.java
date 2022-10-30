package com.mykola.eshopmykola.models.product;

import com.mykola.eshopmykola.dtos.product.ProductDTO;
import com.mykola.eshopmykola.models.order.OrderToProduct;
import com.mykola.eshopmykola.models.user.Bucket;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "price")
	private double price;

	@Column(name="presence")
	private boolean presence;

	@OneToMany(mappedBy = "product")
	@Cascade({org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private List<OrderToProduct> ordersToProduct;

	@ManyToMany
	@JoinTable(name = "buckets_products",
			joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "bucket_id"))
	private List<Bucket> buckets;

	@ManyToMany
	@Cascade({org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinTable(name = "products_categories",
			joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "product_category_id"))
	private List<ProductCategory> productCategories;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<ProductImage> productImages = new ArrayList<>();


	public Product() {
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isPresence() {
		return presence;
	}

	public void setPresence(boolean presence) {
		this.presence = presence;
	}

	public List<OrderToProduct> getOrdersToProduct() {
		return ordersToProduct;
	}

	public void setOrdersToProduct(List<OrderToProduct> ordersToProduct) {
		this.ordersToProduct = ordersToProduct;
	}

	public List<Bucket> getBuckets() {
		return buckets;
	}

	public void setBuckets(List<Bucket> buckets) {
		this.buckets = buckets;
	}

	public List<ProductCategory> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}

	public List<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}



	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return Double.compare(product.price, price) == 0 && presence == product.presence && Objects.equals(id, product.id) && Objects.equals(title, product.title) && Objects.equals(ordersToProduct, product.ordersToProduct) && Objects.equals(buckets, product.buckets) && Objects.equals(productCategories, product.productCategories);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, price, presence, ordersToProduct, buckets, productCategories);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Product(Long id, String title, double price, boolean presence, List<OrderToProduct> ordersToProduct, List<Bucket> buckets, List<ProductCategory> productCategories, List<ProductImage> productImages) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.presence = presence;
		this.ordersToProduct = ordersToProduct;
		this.buckets = buckets;
		this.productCategories = productCategories;
		this.productImages = productImages;

	}

	public static BuilderProduct builder() {
		return new BuilderProduct();
	}

	public static class BuilderProduct {
		private long id;
		private String title;
		private double price;
		private boolean presence;
		private List<OrderToProduct> ordersToProduct;
		private List<Bucket> buckets;
		private List<ProductCategory> productCategories;
		private List<ProductImage> productImages;

		BuilderProduct() {

		}

		public BuilderProduct id(long id) {
			this.id = id;
			return this;
		}

		public BuilderProduct title(String title) {
			this.title = title;
			return this;
		}

		public BuilderProduct price(double price) {
			this.price = price;
			return this;
		}

		public BuilderProduct presence(boolean presence) {
			this.presence = presence;
			return this;
		}

		public BuilderProduct ordersToProduct(List<OrderToProduct> ordersToProduct) {
			this.ordersToProduct = ordersToProduct;
			return this;
		}

		public BuilderProduct buckets(List<Bucket> buckets) {
			this.buckets = buckets;
			return this;
		}

		public BuilderProduct productCategories(List<ProductCategory> productCategories) {
			this.productCategories = productCategories;
			return this;
		}
		public BuilderProduct productImages(List<ProductImage> productImages) {
			this.productImages = productImages;
			return this;
		}



		public Product build() {
			return new Product(this.id, this.title, this.price, this.presence, this.ordersToProduct, this.buckets, this.productCategories, this.productImages);
		}
	}

}
