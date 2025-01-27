package com.develop.exception;

import com.develop.model.RespModel;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class BusinessException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = -7745501204619729837L;

    @Getter
    private String code;
    @Getter
    private HttpStatus httpStatus;
    @Getter
    private RespModel resp;

    public BusinessException(RespModel resp) {
        super();
        this.httpStatus = HttpStatus.OK;
        this.code = resp.getCode();
        this.resp = resp;
    }

    public BusinessException(HttpStatus httpStatus, RespModel resp) {
        super();
        this.httpStatus = httpStatus;
        this.code = resp.getCode();
        this.resp = resp;
    }

    public BusinessException(HttpStatus httpStatus) {
        super();
        this.httpStatus = httpStatus;
    }

    public BusinessException(Enum<?> code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code.name();
        this.httpStatus = httpStatus;
        this.resp = RespModel.code(code);
    }

    //String code
    public BusinessException(String code) {
        super();
        this.httpStatus = HttpStatus.OK;
        this.code = code;
    }

    public BusinessException(String code,RespModel resp){
        super();
        this.httpStatus = HttpStatus.OK;
        this.code = code;
        this.resp = resp;
    }

}
