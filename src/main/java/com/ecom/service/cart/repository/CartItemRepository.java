package com.ecom.service.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.service.cart.model.CartItemModel;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemModel, Long>{
	
	Optional<CartItemModel> findByCartIdAndProductId(Long cartId, String productId);

}
