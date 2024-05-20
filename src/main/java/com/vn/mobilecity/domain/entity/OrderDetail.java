package com.vn.mobilecity.domain.entity;

import com.vn.mobilecity.domain.entity.common.UserDateAuditing;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "order_details")
public class OrderDetail extends UserDateAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Long price;
    @Column(nullable = false)
    private Long netPrice;

    @ManyToOne
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "FK_DETAIL_ORDER"))
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_option_id", foreignKey = @ForeignKey(name = "FK_DETAIL_OPTION"))
    private ProductOption productOption;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "orderDetail")
    private Review review;

}
