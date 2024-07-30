package com.ecommerce.app.payload;

import org.springframework.http.HttpStatus;

import java.util.Date;


public record AppResponse<T>(
        T payload,
        boolean success,
        Integer statusCode,
        Error error,
        Date serviceTime
) {

    public static <T> AppResponse<T> ok(T payload) {
        return new AppResponse<T>(payload, true, HttpStatus.OK.value(), null, new Date());
    }

    public static <T> AppResponse<T> created(T payload) {
        return new AppResponse<T>(payload, true, HttpStatus.CREATED.value(), null, new Date());
    }


    public static <T> AppResponse<T> created(T payload, Date serviceTime) {
        return new AppResponse<T>(payload, true, HttpStatus.CREATED.value(), null, serviceTime);
    }

    public static <T> AppResponse<T> noContent() {
        return new AppResponse<T>(null, true, HttpStatus.NO_CONTENT.value(), null, new Date());
    }

    public static <T> AppResponse<T> noContent(Date serviceTime) {
        return new AppResponse<T>(null, true, HttpStatus.NO_CONTENT.value(), null, serviceTime);
    }

    public static <T> AppResponse<T> appResponse(T payload, Date serviceTime, HttpStatus status) {
        return new AppResponse<T>(null, true, status.value(), null, serviceTime);
    }

    public static <T> AppResponse<T> appResponse(T payload, HttpStatus status) {
        return new AppResponse<T>(payload, true, status.value(), null, new Date());
    }

    public static <T> AppResponse<T> appResponse(T payload, boolean success, HttpStatus status) {
        return new AppResponse<T>(payload, success, status.value(), null, new Date());
    }

    public static <T> AppResponse<T> failedAppResponse(Error error, HttpStatus status) {
        return new AppResponse<T>(null, false, status.value(), error, new Date());
    }

}
