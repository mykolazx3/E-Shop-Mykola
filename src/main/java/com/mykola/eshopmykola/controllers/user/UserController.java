package com.mykola.eshopmykola.controllers.user;

import com.mykola.eshopmykola.dtos.user.UserDTO;
import com.mykola.eshopmykola.models.user.User;
import com.mykola.eshopmykola.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//@PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
	@GetMapping
	public String userList(Model model){
		model.addAttribute("users", userService.getAll());
		return "user-list";
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@PostAuthorize("isAuthenticated() and #email == authentication.principal.username")
	@GetMapping("/{email}/roles")
	@ResponseBody
	public String getRoles(@PathVariable("email") String email){
		System.out.println("called method getRoles");
		User byEmail = userService.getByEmail(email);
		return byEmail.getRole().name();
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/new")
	public String newUser(Model model){
		model.addAttribute("user", new UserDTO());
		return "user-new";
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@PostMapping("/new")
	public String saveUser(UserDTO userDTO, Model model, HttpSession httpSession){
		if(userService.save(userDTO, httpSession)){
			return "redirect:/";
		} else {
			// TODO
			//  доробити надпис "Password is not equals" щоб показувало в представленні
			model.addAttribute("user", userDTO);
			return "user-new";
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/profile")
	public String profileUser(Model model, Principal principal){
		if (principal == null){
			throw new RuntimeException("You are not authorize 1");
		}
		User user = userService.getByEmail(principal.getName());

		UserDTO userDTO = UserDTO.builder()
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.build();
		model.addAttribute("user",userDTO);
		return "profile";
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@PostMapping("/profile")
	public String updateProfileUser(UserDTO userDTO, Model model, Principal principal){
		if(principal == null || !Objects.equals(principal.getName(), userDTO.getEmail())){
			throw new RuntimeException("You are not authorize 2");
		}

		if(userDTO.getPassword() != null && !userDTO.getPassword().isEmpty() && !Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())){
			model.addAttribute("user", userDTO);
			//TODO треба добавите якесь повідомлення, зробимо це в інший раз
			System.out.println("Паролі не співпадають");
			return "profile";
		}

		userService.updateProfile(userDTO);
		return "redirect:/users/profile";
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
