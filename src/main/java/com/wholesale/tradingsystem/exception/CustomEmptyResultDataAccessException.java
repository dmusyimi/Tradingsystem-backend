package com.wholesale.tradingsystem.exception;

import org.springframework.dao.EmptyResultDataAccessException;

public class CustomEmptyResultDataAccessException extends EmptyResultDataAccessException {

    public CustomEmptyResultDataAccessException(String message) {
        super(message, 1);
    }
}
