package com.kindredgroup.unibetlivetest.exception;

import com.kindredgroup.unibetlivetest.types.Enums.CustomHttpStatus;
import lombok.Data;

@Data

public class CustomHttpStatusException extends RuntimeException{
    private final CustomHttpStatus exception;
    private final String message;

    public CustomHttpStatusException(String message, CustomHttpStatus exception) {
        this.message = message;
        this.exception = exception;
    }
}
