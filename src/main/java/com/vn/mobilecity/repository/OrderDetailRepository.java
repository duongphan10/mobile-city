package com.vn.mobilecity.repository;

import com.vn.mobilecity.domain.entity.Order;
import com.vn.mobilecity.domain.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    List<OrderDetail> getAllByOrder(Order order);

}
