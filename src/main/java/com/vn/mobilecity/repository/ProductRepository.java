package com.vn.mobilecity.repository;

import com.vn.mobilecity.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM products " +
            "WHERE ?1 IS NULL OR category_id = ?1", nativeQuery = true)
    List<Product> getAll(Integer categoryId);

    @Query(value = "SELECT * FROM products " +
            "WHERE LOWER(products.name) LIKE LOWER(CONCAT('%', ?1, '%'))", nativeQuery = true)
    List<Product> search(String keyword);

}
