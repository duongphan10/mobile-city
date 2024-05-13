package com.vn.mobilecity.repository;

import com.vn.mobilecity.domain.entity.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlideRepository extends JpaRepository<Slide, Integer> {
    @Query(value = "SELECT slides.* FROM slides " +
            "WHERE ?1 IS NULL OR status = ?1 " +
            "ORDER BY position ASC ", nativeQuery = true)
    List<Slide> getAll(Boolean status);

    @Query(value = "SELECT * FROM slides " +
            "WHERE status = true " +
            "ORDER BY position ASC", nativeQuery = true)
    List<Slide> getSlideByUser();

    boolean existsByPosition(Integer position);
}
