package com.ecom.service.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.service.cart.data.CartData;
import com.ecom.service.cart.data.CartRequestData;
import com.ecom.service.cart.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartApi {
	
	@Autowired 
	private CartService cartService;

	@PostMapping("/add")
	public String addToCart(@RequestBody CartRequestData requestData) {
		cartService.addToCart(requestData);
		return "Item added to cart Successfully!";
		
	}
	
	@GetMapping("/showcart/{cartId}")
	public CartData showCartData(@PathVariable Long cartId) {
		return cartService.showCart(cartId);
	}
}
