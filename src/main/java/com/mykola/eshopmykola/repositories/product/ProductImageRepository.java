package com.mykola.eshopmykola.repositories.product;

import com.mykola.eshopmykola.models.product.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
