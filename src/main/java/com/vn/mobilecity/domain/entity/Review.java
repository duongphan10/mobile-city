package com.vn.mobilecity.domain.entity;

import com.vn.mobilecity.domain.entity.common.UserDateAuditing;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "reviews")
public class Review extends UserDateAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer star;

    @Column(nullable = false)
    private String description;

    @OneToOne
    @JoinColumn(name = "order_detail_id", foreignKey = @ForeignKey(name = "FK_REVIEW_ORDER_DETAIL"))
    private OrderDetail orderDetail;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "review")
    private List<ReviewFile> files;

}
