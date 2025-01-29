package com.develop.exception;

import com.develop.constant.RespCode;
import com.develop.model.RespModel;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class BusinessException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = -7745501204619729837L;

    private String code;

    private RespModel resp;

    public BusinessException(RespModel resp) {
        super();
        this.code = resp.getCode();
        this.resp = resp;
    }

    public BusinessException(HttpStatus httpStatus, RespModel resp) {
        super();
        this.code = resp.getCode();
        this.resp = resp;
    }


    public BusinessException(RespCode code, String message) {
        super(message);
        this.code = code.name();
        this.resp = RespModel.code(code);
    }


}
