package com.mykola.eshopmykola.mappers.user;

import com.mykola.eshopmykola.dtos.user.UserDTO;
import com.mykola.eshopmykola.models.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	public UserDTO fromUser(User user){
		return UserDTO.builder()
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.build();
	}
}
