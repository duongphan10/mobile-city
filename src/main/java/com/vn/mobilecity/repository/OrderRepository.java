package com.vn.mobilecity.repository;

import com.vn.mobilecity.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM orders o " +
            "WHERE " +
            "   o.created_by = ?1 " +
            "   AND (?2 = -1 OR o.order_status_id = ?2) " +
            "ORDER BY o.created_date DESC ", nativeQuery = true)
    List<Order> getAllByUserId(Integer userId, Integer statusId);

    @Query(value = "SELECT * FROM orders o " +
            "LEFT JOIN order_status s ON o.order_status_id = s.id " +
            "LEFT JOIN payment_types p ON o.payment_type_id = p.id " +
            "WHERE " +
            "   (?1 = -1 OR o.order_status_id = ?1) " +
            "   AND (?2 = -1 OR o.payment_type_id = ?2) " +
            "ORDER BY o.created_date DESC ", nativeQuery = true)
    List<Order> getAll(Integer statusId, Integer paymentTypeId);

}
