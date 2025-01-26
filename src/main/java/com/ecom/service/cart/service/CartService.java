package com.ecom.service.cart.service;

import com.ecom.service.cart.data.CartData;
import com.ecom.service.cart.data.CartRequestData;

public interface CartService {

	void addToCart(CartRequestData requestData);
	
	CartData showCart(Long cartId);

}
