package com.mykola.eshopmykola.controllers.product;

import com.mykola.eshopmykola.models.product.ProductImage;
import com.mykola.eshopmykola.repositories.product.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
public class ProductImageController {
	private final ProductImageRepository productImageRepository;

	@Autowired
	public ProductImageController(ProductImageRepository productImageRepository) {
		this.productImageRepository = productImageRepository;
	}

	@GetMapping("/images/{id}")
	private ResponseEntity<?> getImageById(@PathVariable Long id){
		ProductImage productImage = productImageRepository.findById(id).orElse(null);
		return ResponseEntity.ok()
				.header("fileName", productImage.getOriginalFileName())
				.contentType(MediaType.valueOf(productImage.getContentType()))
				.contentLength(productImage.getSize())
				.body(new InputStreamResource(new ByteArrayInputStream(productImage.getBytes())));
	}
}
