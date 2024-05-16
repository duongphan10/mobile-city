package com.vn.mobilecity.repository;

import com.vn.mobilecity.domain.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Integer> {
    @Query(value = "SELECT * FROM product_options " +
            "WHERE ?1 IS NULL OR product_id = ?1", nativeQuery = true)
    List<ProductOption> getAllByProductId(Integer productId);

    @Query(value = "SELECT * FROM product_options po " +
            "WHERE " +
            "   po.product_id = ?1 " +
            "   AND (?2 = -1 OR po.ram = ?2) " +
            "   AND (?3 = -1 OR po.storage_capacity = ?3) " +
            "   AND (?4 = '' OR po.color = ?4) ", nativeQuery = true)
    List<ProductOption> search(Integer productId, Integer ram, Integer rom, String color);
}
