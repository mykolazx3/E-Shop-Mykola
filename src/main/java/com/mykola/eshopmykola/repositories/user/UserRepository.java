package com.mykola.eshopmykola.repositories.user;

import com.mykola.eshopmykola.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findFirstByEmail(String email);
	User findFirstByUuid(String uuid);
	List<User> findAllByEmailNotNull();
}
