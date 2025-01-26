package com.ecom.service.cart.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemData {
	private String cartItemId;
	private String productId;
	private String skuId;
	private Integer quantity;
	private Double price;
	
	
	
}
