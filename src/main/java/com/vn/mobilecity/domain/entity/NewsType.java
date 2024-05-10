package com.vn.mobilecity.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "news_types")
public class NewsType extends UserDateAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "newsType")
    private List<News> news;


}
