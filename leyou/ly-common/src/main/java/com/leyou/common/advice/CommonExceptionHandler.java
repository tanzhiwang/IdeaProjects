package com.leyou.common.advice;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice//默认自动拦截所有controller的
public class CommonExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(LyException e){
        ExceptionEnum em = e.getExceptionEnum();
        //return ResponseEntity.status(400).body();
        return ResponseEntity.status(em.getCode()).body(em.getMsg());

    }
}
