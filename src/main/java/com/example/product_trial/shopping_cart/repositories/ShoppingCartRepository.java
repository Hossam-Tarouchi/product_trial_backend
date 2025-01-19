package com.example.product_trial.shopping_cart.repositories;

import com.example.product_trial.shopping_cart.models.ShoppingCartEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {

    @Transactional
    void deleteByUserIdAndProductId(Long userId, Long productId);
}
