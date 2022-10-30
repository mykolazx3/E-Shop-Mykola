package com.mykola.eshopmykola.services.user;

import com.mykola.eshopmykola.dtos.user.UserDTO;
import com.mykola.eshopmykola.mappers.user.UserMapper;
import com.mykola.eshopmykola.models.user.Role;
import com.mykola.eshopmykola.models.user.User;
import com.mykola.eshopmykola.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;

	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	public boolean save(UserDTO userDTO, HttpSession httpSession) {
		if (!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())) {
			//return false;
			throw new RuntimeException("Password is not equals"); //- непрацює тут
		}

		if (userDTO.getRole() == null) {
			userDTO.setRole(Role.CLIENT);
		}

		String uuid = httpSession.getAttribute("myID").toString();

		User user;
		if (userRepository.findFirstByUuid(uuid) == null) {
			user = User.builder()
					.firstName(userDTO.getFirstName())
					.lastName(userDTO.getLastName())
					.email(userDTO.getEmail())
					.password(passwordEncoder.encode(userDTO.getPassword()))
					.role(userDTO.getRole())
					.build();
			user.setUuid(uuid);
		} else {
			user = userRepository.findFirstByUuid(uuid);
			user.setFirstName(userDTO.getFirstName());
			user.setLastName(userDTO.getLastName());
			user.setEmail(userDTO.getEmail());
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			user.setRole(userDTO.getRole());
		}

		userRepository.save(user);
		return true;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	public void save(User user) {
		userRepository.save(user);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public List<UserDTO> getAll() {
		List<User> users = userRepository.findAllByEmailNotNull();
		List<UserDTO> userDTOs = new ArrayList<>();

		for (User u : users) {
			userDTOs.add(userMapper.fromUser(u));
		}
		//todo переробити в мапер нормальний
		return userDTOs;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public User getByEmail(String email) {
		return userRepository.findFirstByEmail(email);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public User getByUuid(String uuid) {
		return userRepository.findFirstByUuid(uuid);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	public void updateProfile(UserDTO userDTO) {
		User savedUser = userRepository.findFirstByEmail(userDTO.getEmail());
		if (savedUser == null) {
			throw new RuntimeException("User not found by email " + userDTO.getEmail());
		}

		boolean isChanged = false;
		if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
			savedUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			isChanged = true;
		}

		if (!Objects.equals(userDTO.getFirstName(), savedUser.getFirstName())) {
			savedUser.setFirstName(userDTO.getFirstName());
			isChanged = true;
		}

		if (!Objects.equals(userDTO.getLastName(), savedUser.getLastName())) {
			savedUser.setLastName(userDTO.getLastName());
			isChanged = true;
		}

		if (isChanged) {
			userRepository.save(savedUser);
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getCurrentUuid(HttpSession httpSession){
		if (httpSession.getAttribute("myID") == null) {
			String uuid = UUID.randomUUID().toString();
			httpSession.setAttribute("myID", uuid);
		}
		return httpSession.getAttribute("myID").toString();
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getPrincipalOrAnonymousUuid(Principal principal, HttpSession httpSession) {

		String uuid = httpSession.getAttribute("myID").toString();

		if (principal == null) {
			if (userRepository.findFirstByUuid(uuid) == null || userRepository.findFirstByUuid(uuid).getUuid().isBlank()) {
				User user = User.builder().role(Role.GUEST).build();
				user.setUuid(uuid);
				userRepository.save(user);
			}
		} else {
			if (userRepository.findFirstByEmail(principal.getName()).getUuid() == null || userRepository.findFirstByEmail(principal.getName()).getUuid().isBlank()) {
				userRepository.findFirstByEmail(principal.getName()).setUuid(uuid);
			} else {
				uuid = userRepository.findFirstByEmail(principal.getName()).getUuid();
			}
		}
		return uuid;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
