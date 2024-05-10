package com.vn.mobilecity.repository;

import com.vn.mobilecity.domain.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Integer> {
    @Query(value = "SELECT * FROM codes WHERE user_id = ?1", nativeQuery = true)
    OTP findByUserId(Integer userId);

}
