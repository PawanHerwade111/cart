package com.ecom.service.cart.data;

import lombok.Data;

@Data
public class CartData {
	
	private String userId;
	private String productId;
	private String skuId;
	private Integer quantity;
	private Double price;

}
