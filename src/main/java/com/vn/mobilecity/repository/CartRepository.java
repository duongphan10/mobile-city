package com.vn.mobilecity.repository;

import com.vn.mobilecity.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query(value = "SELECT * FROM carts " +
            "WHERE user_id = ?1 " +
            "ORDER BY created_date DESC ", nativeQuery = true)
    List<Cart> getAllByUser(Integer userId);

    @Query(value = "SELECT COUNT(*) FROM carts " +
            "WHERE user_id = ?1 ", nativeQuery = true)
    int countTotalItem(Integer userId);

    @Query(value = "SELECT * FROM carts " +
            "WHERE user_id = ?1 AND product_option_id = ?2 ", nativeQuery = true)
    Cart findByUserIdAndProductOptionId(Integer userId, Integer productOptionId);
}
