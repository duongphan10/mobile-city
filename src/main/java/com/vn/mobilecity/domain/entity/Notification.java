package com.vn.mobilecity.domain.entity;

import com.vn.mobilecity.domain.entity.common.DateAuditing;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "notifications")
public class Notification extends DateAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private String chatId;

    @Column(nullable = true)
    private String topicId;

}
