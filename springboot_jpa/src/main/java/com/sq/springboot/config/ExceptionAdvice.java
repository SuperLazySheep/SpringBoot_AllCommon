package com.sq.springboot.config;

import com.sq.springboot.model.bean.common.Msg;
import com.sq.springboot.model.bean.common.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description 统一异常处理
 * @author kiki
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({Exception.class})
    public ResultData exception(Exception e) {
       /* if (e instanceof AuthorizationException) {
            log.error(e.getMessage());
            return new ResultData(e instanceof UnauthorizedException ? Msg.NO_PERMISSION : Msg.AUTH_ERROR);
        }*/ if (e instanceof ValidationException
                || e instanceof BindException
                || e instanceof MissingServletRequestParameterException) {
            log.error(e.getMessage());
            return new ResultData(Msg.PARAM, e.getMessage());
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            log.error(e.getMessage());
            return new ResultData(Msg.METHOD_NOT_ALLOWED, e.getMessage());
        }/*else if (e instanceof UnknownSessionException) {
            log.error(e.getMessage());
            return new ResultData(Msg.INVALID_SESSION, e.getMessage());
        }*/else if (e instanceof MethodArgumentNotValidException){
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            StringBuffer sb = new StringBuffer();
            if (bindingResult.hasErrors()){
                List<ObjectError> errorList = bindingResult.getAllErrors();
                errorList.forEach(error ->{
                    FieldError fieldError = (FieldError) error;
                    sb.append(fieldError.getField() + ":" + fieldError.getDefaultMessage() + ";");
                });
            }
            log.error(sb.toString());
            return new ResultData<>(sb.toString(), Msg.ERROR_VALIDATION);
        }
        String err = Arrays.stream(e.getStackTrace())
                .filter(it -> it.getClassName().contains("dispute"))
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n"));
        String msg = e.toString() + "\n" + err;
        log.error(msg);
        return new ResultData(Msg.ERROR, msg);
    }


}
