package com.system.pagos.advisor;

import com.system.pagos.exception.BadResquestException;
import com.system.pagos.exception.CommonError;
import com.system.pagos.exception.InternalServerErrorException;
import com.system.pagos.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class AdviceResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CommonError> handleEmptyResultConflict(RecordNotFoundException ex) {
        CommonError error = CommonError.builder().
                errors(ex.getError())
                .process(ex.getProcess())
                .message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(BadResquestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CommonError> handleHttpMessageNotReadable(BadResquestException ex) {
        CommonError error = CommonError.builder().
                errors(ex.getError())
                .process(ex.getProcess())
                .message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CommonError> handleInternalException(InternalServerErrorException ex) {
        CommonError error = CommonError.builder()
                .errors(ex.getError())
                .process("Error en servidor")
                .message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}