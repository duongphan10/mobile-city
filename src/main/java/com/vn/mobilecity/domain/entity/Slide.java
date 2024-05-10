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
@Table(name = "slides")
public class Slide extends UserDateAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String avatar;

    @Column(nullable = false)
    private Integer position;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Boolean status;

}
