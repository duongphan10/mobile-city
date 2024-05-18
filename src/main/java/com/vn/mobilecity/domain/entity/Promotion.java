package com.vn.mobilecity.domain.entity;

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
@Table(name = "promotions")
public class Promotion extends DateAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true)
    private String description;

    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL)
    private List<Product> products;

}
