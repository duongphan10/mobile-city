package com.vn.mobilecity.repository;

import com.vn.mobilecity.domain.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM orders o " +
            "WHERE " +
            "   o.created_by = ?1 ", nativeQuery = true)
    Page<Order> getAllByUserId(Integer userId, Pageable pageable);

    @Query(value = "SELECT * FROM orders o " +
            "WHERE " +
            "   ?1 = '' " +
            "   OR LOWER(o.created_by) LIKE LOWER(CONCAT('%', ?1, '%')) ", nativeQuery = true)
    Page<Order> getAll(String keyword, Pageable pageable);

}
