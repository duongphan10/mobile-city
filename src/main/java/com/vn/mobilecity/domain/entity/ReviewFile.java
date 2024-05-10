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
@Table(name = "review_files")
public class ReviewFile extends UserDateAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long size;

    @ManyToOne
    @JoinColumn(name = "review_id", foreignKey = @ForeignKey(name = "FK_FILE_REVIEW"))
    private Review review;

}
