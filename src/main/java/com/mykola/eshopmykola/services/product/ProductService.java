package com.mykola.eshopmykola.services.product;

import com.mykola.eshopmykola.dtos.product.ProductDTO;
import com.mykola.eshopmykola.mappers.product.ProductMapper;
import com.mykola.eshopmykola.models.product.Product;
import com.mykola.eshopmykola.models.product.ProductImage;
import com.mykola.eshopmykola.models.user.Bucket;
import com.mykola.eshopmykola.models.user.User;
import com.mykola.eshopmykola.repositories.product.ProductRepository;
import com.mykola.eshopmykola.services.user.BucketService;
import com.mykola.eshopmykola.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {

	private final ProductMapper productMapper;

	private final ProductRepository productRepository;

	private final UserService userService;
	private final BucketService bucketService;

	@Autowired
	public ProductService(ProductRepository productRepository, ProductMapper productMapper, UserService userService, BucketService bucketService) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
		this.userService = userService;
		this.bucketService = bucketService;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public List<ProductDTO> getAll() {
		return productMapper.fromProductList(productRepository.findAll());
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	public void addToUserBucket(Long productId, String uuid) {
		User user = userService.getByUuid(uuid);

		Bucket bucket = user.getBucket();
		if (bucket == null) {
			Bucket newBucket = bucketService.createBucket(user, productId);
			user.setBucket(newBucket);
			userService.save(user);
		} else {
			bucketService.addProducts(bucket, productId);
		}

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	public void addNewProduct(ProductDTO productDTO, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {

		Product product = productMapper.toProduct(productDTO);
		List<ProductImage> productImageList = new ArrayList<>();

		ProductImage productImage1;
		ProductImage productImage2;
		ProductImage productImage3;

		if (file1.getSize() != 0) {
			productImage1 = toImageEntity(file1);
			productImage1.setProduct(product);
			productImageList.add(productImage1);
		}
		if (file2.getSize() != 0) {
			productImage2 = toImageEntity(file2);
			productImage2.setProduct(product);
			productImageList.add(productImage2);
		}
		if (file3.getSize() != 0) {
			productImage3 = toImageEntity(file3);
			productImage3.setProduct(product);
			productImageList.add(productImage3);
		}

		product.setProductImages(productImageList);

		productRepository.save(product);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private ProductImage toImageEntity(MultipartFile file) throws IOException {
		ProductImage image = new ProductImage();
		image.setName(file.getName());
		image.setOriginalFileName(file.getOriginalFilename());
		image.setContentType(file.getContentType());
		image.setSize(file.getSize());
		image.setBytes(file.getBytes());
		System.out.println(image.getName() + " " + image.getOriginalFileName() + "" + image.getContentType() + " " + image.getSize());
		return image;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void deleteProduct(long id) {
		productRepository.deleteById(id);
	}


}


