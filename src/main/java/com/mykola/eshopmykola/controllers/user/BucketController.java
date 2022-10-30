package com.mykola.eshopmykola.controllers.user;

import com.mykola.eshopmykola.dtos.order.OrderDTO;
import com.mykola.eshopmykola.dtos.user.BucketDTO;
import com.mykola.eshopmykola.models.user.User;
import com.mykola.eshopmykola.services.user.BucketService;
import com.mykola.eshopmykola.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/bucket")
public class BucketController {

	private final BucketService bucketService;
	private final UserService userService;

	@Autowired
	public BucketController(BucketService bucketService, UserService userService) {
		this.bucketService = bucketService;
		this.userService = userService;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping
	public String aboutBucket(Model model, Principal principal, HttpSession httpSession) {
		userService.getCurrentUuid(httpSession);
		String uuid = userService.getPrincipalOrAnonymousUuid(principal ,httpSession);
		BucketDTO bucketDto = bucketService.getBucketByUuid(uuid);
		model.addAttribute("bucket", bucketDto);



		OrderDTO orderDTO = new OrderDTO();
		if(principal != null){
			User registeredUser = userService.getByUuid(userService.getPrincipalOrAnonymousUuid(principal,httpSession));
			orderDTO.setFirstName(registeredUser.getFirstName());
			orderDTO.setLastName(registeredUser.getLastName());
			orderDTO.setEmail(registeredUser.getEmail());
		}

		model.addAttribute("orderDTO", orderDTO);

		return "bucket";
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/{id}/delete-one")
	public String deleteOneFromBucket(@PathVariable("id") Long id, Principal principal, HttpSession httpSession){
		String uuid = userService.getPrincipalOrAnonymousUuid(principal ,httpSession);
		bucketService.deleteOneBucketProductDTO(id, uuid);
		return "redirect:/bucket";
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/{id}/delete-row")
	public String deleteRowFromBucket(@PathVariable("id") Long id, Principal principal, HttpSession httpSession){
		String uuid = userService.getPrincipalOrAnonymousUuid(principal ,httpSession);
		bucketService.deleteRowBucketProductDTO(id, uuid);
		return "redirect:/bucket";
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/delete-all")
	public String deleteAllFromBucket(Principal principal, HttpSession httpSession){
		String uuid = userService.getPrincipalOrAnonymousUuid(principal ,httpSession);
		bucketService.deleteAllBucketProductDTO(uuid);
		return "redirect:/bucket";
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}



