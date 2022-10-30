package com.mykola.eshopmykola.controllers.main;


import com.mykola.eshopmykola.dtos.product.ProductDTO;
import com.mykola.eshopmykola.services.product.ProductService;
import com.mykola.eshopmykola.services.user.UserService;
import com.mykola.eshopmykola.utils.SessionObjectHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Cookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {

	private final ProductService productService;
	private final UserService userService;
	private final SessionObjectHolder sessionObjectHolder;


	@Autowired
	public MainController(ProductService productService, UserService userService, SessionObjectHolder sessionObjectHolder) {
		this.productService = productService;
		this.userService = userService;
		this.sessionObjectHolder = sessionObjectHolder;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping({"", "/"})
	public String index(Model model, Principal principal, HttpSession httpSession) {

		model.addAttribute("uuid", userService.getCurrentUuid(httpSession));
		model.addAttribute("currentPersonUuid", userService.getPrincipalOrAnonymousUuid(principal, httpSession));

		model.addAttribute("amountClicks", sessionObjectHolder.getAmountClicks());


		List<ProductDTO> list = productService.getAll();
		model.addAttribute("products", list);

		return "index";
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping("/login")
	public String login(Principal principal, HttpSession httpSession) {

		return "login";
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping("/login-error")//щоб користувач попав на 404-page
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



}
