package com.vn.mobilecity.domain.dto.response;

import com.vn.mobilecity.domain.dto.common.UserDateAuditingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto extends UserDateAuditingDto {
    private Integer id;
    private String name;
    private String avatar;

    private String screenTechnology;
    private String screenResolution;
    private String widescreen;
    private String maximumBrightness;
    private String touchGlass;

    private String rearCamera;
    private Boolean flashLight;
    private String rearCameraFeature;
    private String frontCamera;
    private String frontCameraFeature;

    private String operationSystem;
    private String cpu;
    private String cpuSpeed;
    private String graphicChip;

    private String mobileNetwork;
    private String sim;
    private String wifi;
    private String gps;
    private String bluetooth;
    private String chargingPort;
    private String headphoneJack;
    private String otherPort;

    private String batteryCapacity;
    private String batteryType;
    private String maximumChargingSupport;
    private String batteryTechnology;

    private String advancedSecurity;
    private String specialFeature;
    private String waterDustResistance;
    private String record;
    private String movieSupport;
    private String musicSupport;

    private String design;
    private String material;
    private String size;
    private String launchDate;
    private String description;

    private Integer categoryId;
    private String categoryName;

    private Integer promotionId;
    private String promotionName;
    private Long promotionValue;
    private Boolean status;

    private Integer numberReview;
    private Double star;


    private List<ProductOptionDto> productOptionDtos;
}
