package com.vn.mobilecity.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestData<T> {

    private RestStatus status;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private T message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static RestData<?> success(Object data) {
        return new RestData<>(RestStatus.SUCCESS, "Thành công", data);
    }

    public static RestData<?> success(Object message, Object data) {
        return new RestData<>(RestStatus.SUCCESS, message, data);
    }

    public static RestData<?> error(Object message) {
        return new RestData<>(RestStatus.ERROR, message, null);
    }

    public static RestData<?> error(Object message, Object data) {
        return new RestData<>(RestStatus.ERROR, message, data);
    }

}
