package com.develop.model;

import com.develop.constant.RespCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespModel<T> {
    private String code;
    private String description;

    private Long total;
    private Integer page;
    private Integer size;

    private T data;

    private Map<String, Object> additionalData;
    private List<RespErrorModel> errors;
    private RespErrorModel error;

    private String traceId;
    private String requestid;

    public RespModel(Enum<?> code) {
        this.code = code.name();
//        this.description = I18nService.getMessage(code.name());
    }

    public RespModel(Enum<?> code, T data) {
        this.code = code.name();
//        this.description = I18nService.getMessage(code.name());
        this.data = data;
    }

    public RespModel(Enum<?> code, String resMessage) {
        this.code = code.name();
        this.description = resMessage;
    }

    public RespModel(Enum<?> code, String resMessage, T resData) {
        this.code = code.name();
        this.description = resMessage;
        this.data = resData;
    }

    public RespModel(Enum<?> code, String resMessage, String requestid, String traceId) {
        this.code = code.name();
        this.description = resMessage;
        this.traceId = traceId;
        this.requestid = requestid;
    }

    public RespModel(String code, String resMessage) {
        this.code = code;
        this.description = resMessage;
    }

    public RespModel(String code, String resMessage, T resData) {
        this.code = code;
        this.description = resMessage;
        this.data = resData;
    }

    public RespModel(String code, String resMessage, String requestid, String traceId) {
        this.code = code;
        this.description = resMessage;
        this.traceId = traceId;
        this.requestid = requestid;
    }

    public static <T> RespModel<T> success() {
        return new RespModel<>(RespCode.SUCCESS);
    }

    public static <T> RespModel<T> success(T data) {
        return new RespModel<>(RespCode.SUCCESS, data);
    }

    public static <T> RespModel<T> code(Enum<?> code) {
        return new RespModel<>(code);
    }

    public static <T> RespModel<T> code(Enum<?> code, T data) {
        return new RespModel<>(code, data);
    }

    public static <T> RespModel<T> error(Enum<?> code) {
        return new RespModel<>(code);
    }

    public static <T> RespModel<T> error(Enum<?> code, String defaultMessage) {
        return new RespModel<>(code, defaultMessage);
    }

    public static <T> RespModel<T> error(Enum<?> code, String defaultMessage, T data) {
        return new RespModel<>(code, defaultMessage, data);
    }

    public Boolean isSuccess() {
        return RespCode.SUCCESS.name().equals(this.code) || "00".equals(this.code) || "000".equals(this.code);
    }
//
//    public static <T> RespModel<T> from(Objects data) {
//        return JsonUtils.convertValue(data, RespModel.class);
//    }
//
//    public static <T> RespModel<T> from(String data) {
//        return JsonUtils.toObject(data, RespModel.class);
//    }

}

