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
@Table(name = "product_options")
public class ProductOption extends UserDateAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer ram;
    @Column(nullable = false)
    private Integer storageCapacity;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private Long price;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Long promotionValue;
    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_PRODUCT_OPTION_PRODUCT"))
    private Product product;

    @ManyToOne
    @JoinColumn(name = "promotion_id", foreignKey = @ForeignKey(name = "FK_PRODUCT_OPTION_PROMOTION"))
    private Promotion promotion;

    @OneToMany(mappedBy = "productOption", cascade = CascadeType.ALL)
    private List<Cart> carts;

    @OneToMany(mappedBy = "productOption", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

}
