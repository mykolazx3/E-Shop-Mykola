package com.mykola.eshopmykola.dtos.user;

import com.mykola.eshopmykola.models.product.Product;

public class BucketProductDTO {
	private String title;
	private long productId;
	private double price;
	private int amount;
	private double sum;

	public BucketProductDTO(Product product) {
		this.title = product.getTitle();
		this.productId = product.getId();
		this.price = product.getPrice();
		this.amount = 1;
		this.sum = product.getPrice();
	}

	public BucketProductDTO() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////










//	public BucketDetailDTO(String title, long productId, double price, double amount, double sum) {
//		this.title = title;
//		this.productId = productId;
//		this.price = price;
//		this.amount = amount;
//		this.sum = sum;
//	}
//
//	public static BuilderBucketDetailDTO builder() {
//		return new BuilderBucketDetailDTO();
//	}
//
//	public static class BuilderBucketDetailDTO {
//		private String title;
//		private long productId;
//		private double price;
//		private double amount;
//		private double sum;
//
//		BuilderBucketDetailDTO() {
//
//		}
//
//		public BuilderBucketDetailDTO title(String title) {
//			this.title = title;
//			return this;
//		}
//		public BuilderBucketDetailDTO productId(long productId) {
//			this.productId = productId;
//			return this;
//		}
//		public BuilderBucketDetailDTO price(double price) {
//			this.price = price;
//			return this;
//		}
//		public BuilderBucketDetailDTO amount(double amount) {
//			this.amount = amount;
//			return this;
//		}
//		public BuilderBucketDetailDTO sum(double sum) {
//			this.sum = sum;
//			return this;
//		}
//
//		public BucketDetailDTO build() {
//			return new BucketDetailDTO(this.title, this.productId, this.price, this.amount, this.sum);
//		}
//	}
}
