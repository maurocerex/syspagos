package com.system.pagos.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class RecordNotFoundException extends DemoDataException {

    public RecordNotFoundException(List<String>  error) {
        super(error);
    }

    public RecordNotFoundException(String message, List<String>  error) {
        super(message, error);
    }

    public RecordNotFoundException(String message, String process) {
        super(message, process);
    }

    public RecordNotFoundException(String message, String process, List<String> error) {
        super(message, process, error);
    }
}
