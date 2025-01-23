package com.ecom.service.cart.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.service.cart.data.CartData;
import com.ecom.service.cart.model.CartItemModel;
import com.ecom.service.cart.model.CartModel;
import com.ecom.service.cart.repository.CartItemRepository;
import com.ecom.service.cart.repository.CartRepository;
import com.ecom.service.cart.service.CartService;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;

	@Override
	public void addToCart(CartData requestData) {
		CartModel cart = cartRepository.findByUserId(requestData.getUserId()).orElseGet(() -> {
			CartModel model = new CartModel();
			model.setUserId(requestData.getUserId());
			return cartRepository.save(model);
		});
		Optional<CartItemModel> existingItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), requestData.getProductId());
		
		if(existingItem.isPresent()) {
			CartItemModel item = existingItem.get(); 
			item.setQuantity(item.getQuantity() + requestData.getQuantity());
			cartItemRepository.save(item);
		}else {
			CartItemModel newCartItem = new CartItemModel();
			newCartItem.setPrice(requestData.getPrice());
			newCartItem.setProductId(requestData.getProductId());
			newCartItem.setQuantity(requestData.getQuantity());
			newCartItem.setSkuId(requestData.getSkuId());
			cart.addItem(newCartItem);
			cartRepository.save(cart);
			
		}
		
	}
	

}
