package com.vn.mobilecity.domain.dto.request;

import com.vn.mobilecity.constant.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationRequestDto {
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String name;
    private String description;
    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private Boolean status;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String token;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String chatId;
    private String topicId;

}
