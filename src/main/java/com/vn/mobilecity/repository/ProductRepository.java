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
            "   AND (?2 = -1 OR p.category_id = ?2) " +
            "   AND (po.new_price >= ?3 AND po.new_price <= ?4) " +
            "   AND (po.ram >= ?5 AND po.ram <= ?6) " +
            "   AND (po.storage_capacity >= ?7 AND po.storage_capacity <= ?8) " +
            "GROUP BY p.id ", nativeQuery = true)
    List<Product> search(String keyword, Integer categoryId, Long priceFrom, Long priceTo, Long ramFrom, Long ramTo, Long romFrom, Long romTo, Long sort);


    @Query(value = "SELECT p.*, MIN(po.new_price) as min_price FROM products p " +
            "LEFT JOIN product_options po ON po.product_id = p.id " +
            "WHERE " +
            "   LOWER(p.name) LIKE LOWER(CONCAT('%', ?1, '%')) " +
            "   AND (?2 = -1 OR p.category_id = ?2) " +
            "   AND (po.new_price >= ?3 AND po.new_price <= ?4) " +
            "   AND (po.ram >= ?5 AND po.ram <= ?6) " +
            "   AND (po.storage_capacity >= ?7 AND po.storage_capacity <= ?8) " +
            "GROUP BY p.id " +
            "ORDER BY " +
            "      min_price DESC ", nativeQuery = true)
    List<Product> search1(String keyword, Integer categoryId, Long priceFrom, Long priceTo, Long ramFrom, Long ramTo, Long romFrom, Long romTo, Long sort);

    @Query(value = "SELECT p.*, MIN(po.new_price) as min_price FROM products p " +
            "LEFT JOIN product_options po ON po.product_id = p.id " +
            "WHERE " +
            "   LOWER(p.name) LIKE LOWER(CONCAT('%', ?1, '%')) " +
            "   AND (?2 = -1 OR p.category_id = ?2) " +
            "   AND (po.new_price >= ?3 AND po.new_price <= ?4) " +
            "   AND (po.ram >= ?5 AND po.ram <= ?6) " +
            "   AND (po.storage_capacity >= ?7 AND po.storage_capacity <= ?8) " +
            "GROUP BY p.id " +
            "ORDER BY " +
            "      min_price ASC ", nativeQuery = true)
    List<Product> search2(String keyword, Integer categoryId, Long priceFrom, Long priceTo, Long ramFrom, Long ramTo, Long romFrom, Long romTo, Long sort);

    @Query(value = "SELECT p.* FROM products p " +
            "LEFT JOIN product_options po ON po.product_id = p.id " +
            "WHERE " +
            "   LOWER(p.name) LIKE LOWER(CONCAT('%', ?1, '%')) " +
            "   AND (?2 = -1 OR p.category_id = ?2) " +
            "   AND (po.new_price >= ?3 AND po.new_price <= ?4) " +
            "   AND (po.ram >= ?5 AND po.ram <= ?6) " +
            "   AND (po.storage_capacity >= ?7 AND po.storage_capacity <= ?8) " +
            "GROUP BY p.id " +
            "ORDER BY " +
            "      p.name ASC ", nativeQuery = true)
    List<Product> search3(String keyword, Integer categoryId, Long priceFrom, Long priceTo, Long ramFrom, Long ramTo, Long romFrom, Long romTo, Long sort);

    @Query(value = "SELECT p.* FROM products p " +
            "LEFT JOIN product_options po ON po.product_id = p.id " +
            "WHERE " +
            "   LOWER(p.name) LIKE LOWER(CONCAT('%', ?1, '%')) " +
            "   AND (?2 = -1 OR p.category_id = ?2) " +
            "   AND (po.new_price >= ?3 AND po.new_price <= ?4) " +
            "   AND (po.ram >= ?5 AND po.ram <= ?6) " +
            "   AND (po.storage_capacity >= ?7 AND po.storage_capacity <= ?8) " +
            "GROUP BY p.id " +
            "ORDER BY " +
            "      p.name DESC " , nativeQuery = true)
    List<Product> search4(String keyword, Integer categoryId, Long priceFrom, Long priceTo, Long ramFrom, Long ramTo, Long romFrom, Long romTo, Long sort);
}
