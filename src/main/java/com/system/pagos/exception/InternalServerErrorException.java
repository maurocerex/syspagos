package com.system.pagos.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class InternalServerErrorException extends DemoDataException {

    public InternalServerErrorException(List<String>  error) {
        super(error);
    }

    public InternalServerErrorException(String message, String process) {
        super(message, process);
    }

    public InternalServerErrorException(String message, List<String>  error) {
        super(message, error);
    }


}
