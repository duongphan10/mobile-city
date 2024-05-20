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
@Table(name = "products")
public class Product extends UserDateAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String avatar;

    // Screen
    @Column(nullable = false)
    private String screenTechnology;
    @Column(nullable = false)
    private String screenResolution;
    @Column(nullable = false)
    private String widescreen;
    @Column(nullable = false)
    private String maximumBrightness;
    @Column(nullable = false)
    private String touchGlass;

    // Camera
    @Column(nullable = false)
    private String rearCamera;
    @Column(nullable = false)
    private Boolean flashLight;
    @Column(nullable = false)
    private String rearCameraFeature;
    @Column(nullable = false)
    private String frontCamera;
    @Column(nullable = false)
    private String frontCameraFeature;

    // CPU
    @Column(nullable = false)
    private String operationSystem;
    @Column(nullable = false)
    private String cpu;
    @Column(nullable = false)
    private String cpuSpeed;
    @Column(nullable = false)
    private String graphicChip;

    // Connect
    @Column(nullable = false)
    private String mobileNetwork;
    @Column(nullable = false)
    private String sim;
    @Column(nullable = false)
    private String wifi;
    @Column(nullable = false)
    private String gps;
    @Column(nullable = false)
    private String bluetooth;
    @Column(nullable = false)
    private String chargingPort;
    @Column(nullable = false)
    private String headphoneJack;
    @Column(nullable = false)
    private String otherPort;

    // Battery
    @Column(nullable = false)
    private String batteryCapacity;
    @Column(nullable = false)
    private String batteryType;
    @Column(nullable = false)
    private String maximumChargingSupport;
    @Column(nullable = false)
    private String batteryTechnology;

    // Utilities
    @Column(nullable = false)
    private String advancedSecurity;
    @Column(nullable = false)
    private String specialFeature;
    @Column(nullable = false)
    private String waterDustResistance;
    @Column(nullable = false)
    private String record;
    @Column(nullable = false)
    private String movieSupport;
    @Column(nullable = false)
    private String musicSupport;

    // General information
    @Column(nullable = false)
    private String design;
    @Column(nullable = false)
    private String material;
    @Column(nullable = false)
    private String size;
    @Column(nullable = false)
    private String launchDate;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private Long promotionValue;
    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY"))
    private Category category;

    @ManyToOne
    @JoinColumn(name = "promotion_id", foreignKey = @ForeignKey(name = "FK_PRODUCT_PROMOTION"))
    private Promotion promotion;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
    private List<ProductOption> productOptions;

}
