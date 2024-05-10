package com.vn.mobilecity.repository;

import com.vn.mobilecity.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM products", nativeQuery = true)
    Page<Product> getAll(Pageable pageable);

    @Query(value = "SELECT * FROM products WHERE category_id = ?1", nativeQuery = true)
    Page<Product> getAll(Integer categoryId, Pageable pageable);

    @Query(value = "SELECT * FROM products WHERE LOWER(products.name) LIKE LOWER(CONCAT('%', ?1, '%'))", nativeQuery = true)
    Page<Product> search(String keyword, Pageable pageable);

}
