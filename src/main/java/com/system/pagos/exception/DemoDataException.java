package com.system.pagos.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class DemoDataException extends RuntimeException{

    private List<String> error;
    private String process;

    public DemoDataException(List<String> error){
        this.error = error;
    }

    public DemoDataException(String message, String process, List<String> error) {
        super(message);
        this.error = error;
        this.process = process;
    }

    public DemoDataException(String message , List<String>  error){
        super(message);
        this.error = error;
    }

    public DemoDataException(String message , String  process){
        super(message);
        this.process = process;
    }
}
