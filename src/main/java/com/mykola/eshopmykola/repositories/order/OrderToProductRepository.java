package com.mykola.eshopmykola.repositories.order;

import com.mykola.eshopmykola.models.order.OrderToProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderToProductRepository extends JpaRepository<OrderToProduct, Long> {
}
