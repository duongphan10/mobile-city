package com.vn.mobilecity.repository;

import com.vn.mobilecity.domain.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    @Query(value = "SELECT * FROM promotions " +
            "ORDER BY created_date ASC ", nativeQuery = true)
    List<Promotion> getAll();
}
