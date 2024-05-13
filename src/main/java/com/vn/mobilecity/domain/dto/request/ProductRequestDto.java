package com.vn.mobilecity.domain.dto.request;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.validator.annotation.ValidFileImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequestDto {
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String name;
    //@NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    @ValidFileImage
    private MultipartFile avatar;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String screenTechnology;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String screenResolution;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String widescreen;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String maximumBrightness;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String touchGlass;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String rearCamera;
    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private Boolean flashLight;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String rearCameraFeature;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String frontCamera;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String frontCameraFeature;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String operationSystem;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String cpu;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String cpuSpeed;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String graphicChip;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String mobileNetwork;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String sim;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String wifi;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String gps;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String bluetooth;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String chargingPort;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String headphoneJack;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String otherPort;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String batteryCapacity;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String batteryType;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String maximumChargingSupport;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String batteryTechnology;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String advancedSecurity;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String specialFeature;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String waterDustResistance;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String record;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String movieSupport;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String musicSupport;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String design;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String material;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String size;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String launchDate;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String description;

    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private Integer categoryId;

    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private Integer promotionId;
    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private Long promotionValue;
}
