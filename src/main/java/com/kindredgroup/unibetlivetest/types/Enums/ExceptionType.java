package com.kindredgroup.unibetlivetest.types.Enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ExceptionType {

    CUSTOMER_NOT_FOUND(HttpStatus.NOT_FOUND),
    EVENT_NOT_FOUND(HttpStatus.NOT_FOUND),
    BAD_REQUEST(HttpStatus.BAD_REQUEST),
    INSUFFICIENT_BALANCE(HttpStatus.INSUFFICIENT_STORAGE)
    ;


    @Getter
    final HttpStatus status;

    ExceptionType(HttpStatus status) {
        this.status = status;
    }

}
