package com.ecom.service.cart.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "cart_item")
public class CartItemModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@Nonnull
	private String productId;
	
	@Column
	@Nonnull
	private String skuId;
	
	@Column
	@Nonnull
	private Integer quantity;
	
	@Column
	private Double price;
	
	@ManyToOne
	@JoinColumn(name = "cart_id" , nullable = false)
	private CartModel cart;

}
