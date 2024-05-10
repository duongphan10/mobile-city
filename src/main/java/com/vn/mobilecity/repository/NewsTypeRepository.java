package com.vn.mobilecity.repository;

import com.vn.mobilecity.domain.entity.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsTypeRepository extends JpaRepository<NewsType, Integer> {
    @Query(value = "SELECT * FROM news_types " +
            "ORDER BY id ASC ", nativeQuery = true)
    List<NewsType> getAll();
}
