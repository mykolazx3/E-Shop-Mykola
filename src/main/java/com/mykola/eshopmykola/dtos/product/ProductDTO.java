package com.mykola.eshopmykola.dtos.product;


import com.mykola.eshopmykola.models.product.ProductImage;

import java.util.List;

public class ProductDTO {
	private long id;
	private String title;
	private double price;
	private boolean presence;
	private List<ProductImage> productImages;


	public ProductDTO() {
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

	public List<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}





	public ProductDTO(long id, String title, double price, boolean presence, List<ProductImage> productImages) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.presence = presence;
		this.productImages = productImages;

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static BuilderProductDTO builder() {
		return new BuilderProductDTO();
	}

	public static class BuilderProductDTO {
		private long id;
		private String title;
		private double price;
		private boolean presence;
		private List<ProductImage> productImages;
		private Long previewImageId;


		BuilderProductDTO() {

		}

		public BuilderProductDTO id(long id) {
			this.id = id;
			return this;
		}

		public BuilderProductDTO title(String title) {
			this.title = title;
			return this;
		}

		public BuilderProductDTO price(double price) {
			this.price = price;
			return this;
		}

		public BuilderProductDTO presence(boolean presence) {
			this.presence = presence;
			return this;
		}
		public BuilderProductDTO productImages(List<ProductImage> productImages) {
			this.productImages = productImages;
			return this;
		}




		public ProductDTO build() {
			return new ProductDTO(this.id, this.title, this.price, this.presence, this.productImages);
		}
	}
}
