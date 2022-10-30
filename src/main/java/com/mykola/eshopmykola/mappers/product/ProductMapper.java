package com.mykola.eshopmykola.mappers.product;

import com.mykola.eshopmykola.dtos.product.ProductDTO;
import com.mykola.eshopmykola.models.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

	public Product toProduct(ProductDTO productDTO){
		return Product.builder()
				.id(productDTO.getId())
				.title(productDTO.getTitle())
				.price(productDTO.getPrice())
				.presence(productDTO.isPresence())
				.productImages(productDTO.getProductImages())
				.build();
	}

	public ProductDTO fromProduct(Product product){
		return ProductDTO.builder()
				.id(product.getId())
				.title(product.getTitle())
				.price(product.getPrice())
				.presence(product.isPresence())
				.productImages(product.getProductImages())
				.build();
	}

	public List<Product> toProductList(List<ProductDTO> productDTOList){
		List<Product> productList = new ArrayList<>();
		for(ProductDTO productDTO: productDTOList){
			productList.add(toProduct(productDTO));
		}
		return productList;
	}

	public List<ProductDTO> fromProductList(List<Product> productList){
		List<ProductDTO> productDTOList = new ArrayList<>();
		for(Product product: productList){
			productDTOList.add(fromProduct(product));
		}
		return productDTOList;
	}

}
