package com.vn.mobilecity.domain.entity;

import com.vn.mobilecity.domain.entity.common.UserDateAuditing;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "addresses")
public class Address extends UserDateAuditing {
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

    @Nationalized
    @Column(nullable = false)
    private Boolean type;

    @Column(nullable = false)
    private Boolean addressDefault;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_ADDRESS_USER"))
    private User user;
}
