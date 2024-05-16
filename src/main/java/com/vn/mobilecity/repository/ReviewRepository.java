package com.vn.mobilecity.repository;

import com.vn.mobilecity.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query(value = "SELECT * FROM reviews r " +
            "LEFT JOIN order_details od ON r.order_detail_id = od.id " +
            "LEFT JOIN product_options po ON od.product_option_id = po.id " +
            "WHERE " +
            "   po.product_id = ?1 " +
            "   AND (?2 = -1 OR r.star = ?2) ", nativeQuery = true)
    List<Review> search(Integer productId, Integer star);

}
