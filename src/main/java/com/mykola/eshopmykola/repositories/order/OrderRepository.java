package com.mykola.eshopmykola.repositories.order;

import com.mykola.eshopmykola.models.order.Order;
import com.mykola.eshopmykola.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findAllByOrderOwner(User user);
}
