package com.vn.mobilecity.repository;

import com.vn.mobilecity.domain.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    @Query(value = "SELECT news.* FROM news " +
            "WHERE ?1 IS NULL OR status = ?1 ", nativeQuery = true)
    List<News> getAll(Boolean status);

    @Query(value = "SELECT * FROM news WHERE status = true", nativeQuery = true)
    List<News> getByUser();

}
