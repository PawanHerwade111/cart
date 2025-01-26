package com.ecom.service.cart.data;

import java.util.List;

import lombok.Data;

@Data
public class CartData {
	private String userId;
	private String cartId;
	private List<CartItemData> cartItemData;
}
