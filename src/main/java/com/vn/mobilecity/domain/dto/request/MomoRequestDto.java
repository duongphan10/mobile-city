package com.vn.mobilecity.domain.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MomoRequestDto {
    private String orderInfo;
    private String amount;
    private String extraData;
    private String orderGroupId;
}




