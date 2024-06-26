package com.vn.mobilecity.domain.dto.request;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.validator.annotation.ValidListFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewCreateDto {
    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private Integer orderDetailId;
    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private Integer star;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String description;
    @ValidListFile
    private List<MultipartFile> files;

}
