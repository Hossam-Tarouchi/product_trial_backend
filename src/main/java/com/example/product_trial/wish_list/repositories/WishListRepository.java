package com.example.product_trial.wish_list.repositories;

import com.example.product_trial.wish_list.models.WishListEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishListEntity, Long> {

    @Transactional
    void deleteByUserIdAndProductId(Long userId, Long productId);
}
