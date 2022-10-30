package com.mykola.eshopmykola.dtos.user;

import java.util.ArrayList;
import java.util.List;

public class BucketDTO {
	private int amountProducts;
	private double sum;
	private List<BucketProductDTO> bucketProductDTOs = new ArrayList<>();

	public void aggregate(){
		this.amountProducts = bucketProductDTOs.size();

		double sum = 0;
		for(BucketProductDTO bucketProductDTO : bucketProductDTOs){
			sum = sum + bucketProductDTO.getSum();
		}
		this.sum = sum;
	}



	public BucketDTO() {
	}

	public BucketDTO(int amountProducts, double sum, List<BucketProductDTO> bucketProductDTOs) {
		this.amountProducts = amountProducts;
		this.sum = sum;
		this.bucketProductDTOs = bucketProductDTOs;
	}

	public int getAmountProducts() {
		return amountProducts;
	}

	public void setAmountProducts(int amountProducts) {
		this.amountProducts = amountProducts;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public List<BucketProductDTO> getBucketProductDTOs() {
		return bucketProductDTOs;
	}

	public void setBucketProductDTOs(List<BucketProductDTO> bucketProductDTOs) {
		this.bucketProductDTOs = bucketProductDTOs;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////












//	public static BuilderBucketDTO builder() {
//		return new BuilderBucketDTO();
//	}
//
//	public static class BuilderBucketDTO {
//		private int amountProducts;
//		private double sum;
//		private List<BucketDetailDTO> bucketDetails;
//
//		BuilderBucketDTO() {
//
//		}
//
//		public BuilderBucketDTO amountProducts(int amountProducts) {
//			this.amountProducts = amountProducts;
//			return this;
//		}
//		public BuilderBucketDTO sum(double sum) {
//			this.sum = sum;
//			return this;
//		}
//		public BuilderBucketDTO bucketDetails(List<BucketDetailDTO> bucketDetails) {
//			this.bucketDetails = bucketDetails;
//			return this;
//		}
//
//
//		public BucketDTO build() {
//			return new BucketDTO(this.amountProducts, this.sum, this.bucketDetails);
//		}
//	}
}
