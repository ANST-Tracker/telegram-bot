package com.anst.sd.telegram.adapter.rest;

import com.anst.sd.telegram.adapter.rest.dto.ErrorInfoDto;
import com.anst.sd.telegram.app.api.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.anst.sd.telegram.adapter.rest.dto.ErrorInfoDto.createErrorInfo;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handle(UserNotFoundException ex) {
        logger.warn(ex.getMessage(), ex);
        var errorInfo = createErrorInfo(ex);
        errorInfo.setType(ErrorInfoDto.ErrorType.CLIENT);
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
