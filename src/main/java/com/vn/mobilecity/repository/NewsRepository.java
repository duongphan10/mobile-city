package com.vn.mobilecity.repository;

import com.vn.mobilecity.domain.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    @Query(value = "SELECT news.* FROM news n " +
            "WHERE " +
            "   (?1 = -1 OR n.news_type_id = ?1) " +
            "   AND (?2 IS NULL OR n.status = ?2) " +
            "ORDER BY n.created_date DESC ", nativeQuery = true)
    List<News> getAll(Integer newsTypeId, Boolean status);

    @Query(value = "SELECT * FROM news n " +
            "WHERE " +
            "   n.status = true " +
            "   AND (?1 = -1 OR n.news_type_id = ?1) " +
            "ORDER BY n.created_date DESC ", nativeQuery = true)
    List<News> getByUser(Integer newsTypeId);

}
