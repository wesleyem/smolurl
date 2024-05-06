package com.wesleyem.smolurl.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictingIdsGivenException extends RuntimeException {

    public ConflictingIdsGivenException(String message) {
        super();
    }

}
