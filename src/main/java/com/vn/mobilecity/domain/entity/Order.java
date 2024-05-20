package com.vn.mobilecity.domain.entity;

import com.vn.mobilecity.domain.entity.common.UserDateAuditing;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
public class Order extends UserDateAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Nationalized
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false)
    private String phone;
    @Nationalized
    @Column(nullable = false)
    private String address;
    @Column(nullable = true)
    private String note;
    @Column(nullable = false)
    private Long shippingFee;
    @Column(nullable = false)
    private Long originalPrice;
    @Column(nullable = false)
    private Long netPriceTotal;
    @Column(nullable = false)
    private Boolean paymentStatus;

    @ManyToOne
    @JoinColumn(name = "order_status_id", foreignKey = @ForeignKey(name = "FK_ORDER_ORDER_STATUS"))
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "payment_type_id", foreignKey = @ForeignKey(name = "FK_ORDER_PAYMENT_TYPE"))
    private PaymentType paymentType;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_ORDER_USER"))
    private User user;

}
