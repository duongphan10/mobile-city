package com.vn.mobilecity.domain.entity;

import com.vn.mobilecity.domain.entity.common.DateAuditing;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "otps")
public class OTP extends DateAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime expirationTime;

    @Column(nullable = false)
    private Boolean enabled;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

}
