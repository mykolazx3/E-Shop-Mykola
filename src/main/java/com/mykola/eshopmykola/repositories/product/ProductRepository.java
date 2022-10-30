package com.mykola.eshopmykola.repositories.product;

import com.mykola.eshopmykola.models.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findAllByPresence(boolean b);
}
