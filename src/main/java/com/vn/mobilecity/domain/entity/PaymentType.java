package com.vn.mobilecity.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vn.mobilecity.domain.entity.common.DateAuditing;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "payment_types")
public class PaymentType extends DateAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Boolean enabled;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentType")
    private List<Order> orders;

}
