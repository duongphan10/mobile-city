package com.vn.mobilecity.repository;

import com.vn.mobilecity.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM products " +
            "WHERE " +
            "   (?1 IS NULL OR category_id = ?1) " +
            "   AND (?2 IS NULL OR promotion_id = ?2) ", nativeQuery = true)
    List<Product> getAll(Integer categoryId, Integer promotionId);

    @Query(value = "SELECT p.* FROM products p " +
            "LEFT JOIN product_options po ON po.product_id = p.id " +
            "WHERE " +
            "   LOWER(p.name) LIKE LOWER(CONCAT('%', ?1, '%')) " +
            "   AND (po.price >= ?2 AND po.price <= ?3) ", nativeQuery = true)
    List<Product> search(String keyword, Long priceFrom, Long priceTo);

}
