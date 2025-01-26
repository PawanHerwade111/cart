package com.ecom.service.cart.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.service.cart.data.CartData;
import com.ecom.service.cart.data.CartItemData;
import com.ecom.service.cart.data.CartRequestData;
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
	public void addToCart(CartRequestData requestData) {
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
	
	@Override
	public CartData showCart(Long cartId) {
		CartData cartData = new CartData();
		Optional<CartModel> cartModel = cartRepository.findById(cartId);
		if(cartModel.isPresent()) {
			CartModel existingModel = cartModel.get();
			cartData.setUserId(existingModel.getUserId());
			cartData.setCartId(existingModel.getId().toString());
			List<CartItemData> cid = existingModel.getItems().stream()
				    .map(i -> new CartItemData(i.getId().toString(), i.getProductId(), i.getSkuId(),i.getQuantity(), i.getPrice()))
				    .collect(Collectors.toList());
			cartData.setCartItemData(cid);

		}
		return cartData;
	}
	

}
