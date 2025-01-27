package com.develop.constant;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RespCode {

    SUCCESS,
    PROCESSING,
    NOT_FOUND,
    ERR_DUPLICATE,
    ERR_DATA_INVALID,
    UNKNOWN;

}
