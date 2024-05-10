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
@Table(name = "news")
public class News extends UserDateAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String avatar;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "news_type_id", foreignKey = @ForeignKey(name = "FK_NEWS_NEWS_TYPE"))
    private NewsType newsType;

}
