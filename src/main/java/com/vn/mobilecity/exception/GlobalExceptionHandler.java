package com.vn.mobilecity.exception;

import com.vn.mobilecity.base.BaseResponse;
import com.vn.mobilecity.base.RestData;
import com.vn.mobilecity.constant.ErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    //Error validate for param
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestData<?>> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error(ex.getMessage());
//        Map<String, String> result = new LinkedHashMap<>();
//        ex.getConstraintViolations().forEach((error) -> {
//            String fieldName = ((PathImpl) error.getPropertyPath()).getLeafNode().getName();
//            String errorMessage = messageSource.getMessage(Objects.requireNonNull(error.getMessage()), null,
//                    LocaleContextHolder.getLocale());
//            result.put(fieldName, errorMessage);
//        });
        String result = "";
        if (!ex.getConstraintViolations().isEmpty()) {
            ConstraintViolation<?> firstError = ex.getConstraintViolations().iterator().next();

            String fieldName = ((PathImpl) firstError.getPropertyPath()).getLeafNode().getName();
            result = messageSource.getMessage(Objects.requireNonNull(firstError.getMessage()), new String[]{fieldName},
                    LocaleContextHolder.getLocale());
        }
        return BaseResponse.error(HttpStatus.BAD_REQUEST, result);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestData<?>> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error(ex.getMessage());
        String result = "";
        String fieldName = ex.getParameterName();
//        String errorMessage = "Required request parameter " + fieldName + " is not present";
        String errorMessage = "Tham số yêu cầu bắt buộc " + fieldName + " không tồn tại.";
        result = errorMessage;

        return BaseResponse.error(HttpStatus.BAD_REQUEST, result);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestData<?>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage());
        String message = messageSource.getMessage(ErrorMessage.ERR_EXCEPTION_INVALID_JSON_REQUEST, null,
                LocaleContextHolder.getLocale());
        return BaseResponse.error(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestData<?>> handleValidException(BindException ex) {
        log.error(ex.getMessage());
//        Map<String, String> result = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = messageSource.getMessage(Objects.requireNonNull(error.getDefaultMessage()), null,
//                    LocaleContextHolder.getLocale());
//            result.put(fieldName, errorMessage);
//        });

        String result = "";
        ObjectError error = ex.getBindingResult().getAllErrors().get(0);
        String fieldName = ((FieldError) error).getField();
        result = messageSource.getMessage(Objects.requireNonNull(error.getDefaultMessage()), new String[]{fieldName},
                LocaleContextHolder.getLocale());
        return BaseResponse.error(HttpStatus.BAD_REQUEST, result);
    }

    @ExceptionHandler(Exception.class)
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<RestData<?>> handlerInternalServerError(Exception ex) {
        log.error(ex.getMessage());
        String message = messageSource.getMessage(ErrorMessage.ERR_EXCEPTION_GENERAL, null, LocaleContextHolder.getLocale());

        if (ex instanceof DisabledException) {
            message = messageSource.getMessage(ErrorMessage.Auth.ERR_ACCOUNT_NOT_ENABLED, null,
                    LocaleContextHolder.getLocale());
            return BaseResponse.error(HttpStatus.FORBIDDEN, message);
        }

        if (ex instanceof AccessDeniedException) {
            message = messageSource.getMessage(ErrorMessage.FORBIDDEN, null,
                    LocaleContextHolder.getLocale());
            return BaseResponse.error(HttpStatus.FORBIDDEN, message);
        }

        if (ex instanceof MaxUploadSizeExceededException) {
            message = messageSource.getMessage(ErrorMessage.ERR_EXCEPTION_MAX_UPLOAD_FILE_SIZE, null,
                    LocaleContextHolder.getLocale());
            return BaseResponse.error(HttpStatus.BAD_REQUEST, message);
        }

        if (ex instanceof MultipartException) {
            message = messageSource.getMessage(ErrorMessage.ERR_EXCEPTION_MULTIPART, null,
                    LocaleContextHolder.getLocale());
            return BaseResponse.error(HttpStatus.BAD_REQUEST, message);
        }
        return BaseResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    //Exception custom
    @ExceptionHandler(VsException.class)
    public ResponseEntity<RestData<?>> handleVsException(VsException ex) {
        log.error(ex.getMessage(), ex);
        return BaseResponse.error(ex.getStatus(), ex.getErrMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RestData<?>> handlerNotFoundException(NotFoundException ex) {
        String message = messageSource.getMessage(ex.getMessage(), ex.getParams(), LocaleContextHolder.getLocale());
        log.error(message, ex);
        return BaseResponse.error(ex.getStatus(), message);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<RestData<?>> handlerAlreadyExistException(AlreadyExistException ex) {
        String message = messageSource.getMessage(ex.getMessage(), ex.getParams(), LocaleContextHolder.getLocale());
        log.error(message, ex);
        return BaseResponse.error(ex.getStatus(), message);
    }

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<RestData<?>> handlerInvalidException(InvalidException ex) {
        log.error(ex.getMessage(), ex);
        String message = messageSource.getMessage(ex.getMessage(), ex.getParams(), LocaleContextHolder.getLocale());
        return BaseResponse.error(ex.getStatus(), message);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<RestData<?>> handlerInternalServerException(InternalServerException ex) {
        String message = messageSource.getMessage(ex.getMessage(), ex.getParams(), LocaleContextHolder.getLocale());
        log.error(message, ex);
        return BaseResponse.error(ex.getStatus(), message);
    }

    @ExceptionHandler(UploadFileException.class)
    public ResponseEntity<RestData<?>> handleUploadImageException(UploadFileException ex) {
        String message = messageSource.getMessage(ex.getMessage(), ex.getParams(), LocaleContextHolder.getLocale());
        log.error(message, ex);
        return BaseResponse.error(ex.getStatus(), message);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<RestData<?>> handleUnauthorizedException(UnauthorizedException ex) {
        String message = messageSource.getMessage(ex.getMessage(), ex.getParams(), LocaleContextHolder.getLocale());
        log.error(message, ex);
        return BaseResponse.error(ex.getStatus(), message);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<RestData<?>> handleAccessDeniedException(ForbiddenException ex) {
        String message = messageSource.getMessage(ex.getMessage(), ex.getParams(), LocaleContextHolder.getLocale());
        log.error(message, ex);
        return BaseResponse.error(ex.getStatus(), message);
    }

}