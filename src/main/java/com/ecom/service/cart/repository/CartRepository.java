package com.ecom.service.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.service.cart.model.CartModel;

@Repository
public interface CartRepository extends JpaRepository<CartModel, Long>{
	
	Optional<CartModel> findByUserId(String userId);

}
