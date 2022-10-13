package com.system.pagos.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FactoryExceptions {

    /**
     * @param process proccess
     * @param message message
     * @param errors  errors or causes error
     * @param e       exception
     * @return InternalServerErrorException
     */
    public static InternalServerErrorException throwInternalServerError(String process,
                                                                        String message,
                                                                        String errors, Exception e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return new InternalServerErrorException(
                message,
                Collections.singletonList(errors)
        );
    }

    /**
     * @param process proccess
     * @param message message
     * @param errors  errors or causes error
     * @return InternalServerErrorException
     */
    public static InternalServerErrorException throwInternalServerError(
                                                                        String message,
                                                                        String errors) {
        return new InternalServerErrorException(
                message,
                Collections.singletonList(errors)
        );
    }

    public static RecordNotFoundException throwNotFound(String process, String message, String error) {
        return new RecordNotFoundException(
                message,
                process,
                Collections.singletonList(error)
        );
    }

    public static BadRequestException throwBadRequest(String process, String message, String error) {
        return new BadRequestException(
                message,
                process,
                Collections.singletonList(error)
        );
    }
}
