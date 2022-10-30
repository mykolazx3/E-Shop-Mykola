package com.mykola.eshopmykola.controllers.product;

import com.mykola.eshopmykola.dtos.product.ProductDTO;
import com.mykola.eshopmykola.services.product.ProductService;
import com.mykola.eshopmykola.services.user.UserService;
import com.mykola.eshopmykola.utils.SessionObjectHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;
	private final UserService userService;
	private final SessionObjectHolder sessionObjectHolder;

	@Autowired
	public ProductController(ProductService productService, UserService userService, SessionObjectHolder sessionObjectHolder) {
		this.productService = productService;
		this.userService = userService;
		this.sessionObjectHolder = sessionObjectHolder;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping
	public String list(Model model) {

		model.addAttribute("productDTO", new ProductDTO());


		List<ProductDTO> list = productService.getAll();
		model.addAttribute("products", list);

		return "products";
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/{id}/bucket")
	public String addBucket(@PathVariable("id") Long id, Principal principal, HttpSession httpSession) {
		sessionObjectHolder.addClick();

		String uuid = userService.getPrincipalOrAnonymousUuid(principal, httpSession);
		productService.addToUserBucket(id, uuid);
		return "redirect:/";
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@PostMapping("/add")
	public String addNewProduct(@RequestParam(name = "file1", required = false) MultipartFile file1,
	                            @RequestParam(name = "file2", required = false) MultipartFile file2,
	                            @RequestParam(name = "file3", required = false) MultipartFile file3,
	                            ProductDTO productDTO) throws IOException {

		productService.addNewProduct(productDTO, file1, file2, file3);
		System.out.println("гнида");
		return "redirect:/products";
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/{id}/delete")
	public String deleteProduct(@PathVariable("id") long id) {
		productService.deleteProduct(id);
		return "redirect:/products";
	}
}
