package com.up42.codingtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends Exception
{
    static final long serialVersionUID = -3838100498011665370L;


    public EntityNotFoundException(String message)
    {
        super(message);
    }

}
