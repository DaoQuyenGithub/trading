package com.develop.exception;


import com.develop.constant.RespCode;
import com.develop.model.RespModel;
import com.develop.utils.CommonUtils;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
class RestControllerAdvice extends DefaultResponseErrorHandler implements ResponseErrorHandler {

    private final Tracer tracer;

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(HttpServletRequest request, Exception ex) {
        String traceId = tracer.currentSpan() != null ? tracer.currentSpan().context().traceId() : "";

        if (ex instanceof MethodArgumentNotValidException
                || ex instanceof MissingServletRequestParameterException
                || ex instanceof MissingRequestHeaderException
                || ex instanceof HttpMessageNotReadableException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RespModel<>(HttpStatus.BAD_REQUEST.name(),
                            getRespMessage(ex),
                            traceId));
        }

        RespModel respModel;
        if (ex instanceof BusinessException) {
            respModel = new RespModel<>(((BusinessException) ex).getCode(),
                    ex.getMessage(),
                    traceId);
            log.error("[ERROR] response Data={} Error={}", CommonUtils.toJsonString(respModel), ex.getMessage());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(respModel);
        }
        respModel = RespModel.error(RespCode.ERR_UNKNOWN, ex.getMessage());
        respModel.setTraceId(traceId);
        log.error("[ERROR] response Data={} Error={}", CommonUtils.toJsonString(respModel), ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK)
                .body(respModel);

    }

    private String getRespMessage(Exception ex) {
        String msg = "";
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            List<String> errorMessages = new ArrayList<>();
            for (FieldError error : exception.getBindingResult().getFieldErrors()) {
                String fieldName = error.getField();
                String errorMessage = error.getDefaultMessage();
                errorMessages.add(fieldName + ": " + errorMessage);
            }
            msg = String.join(", ", errorMessages);
        }
        if (ex instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException exception = (MissingServletRequestParameterException) ex;
            String paramName = exception.getParameterName();
            msg = "Required parameter '" + paramName + "' is missing.";
        }
        if (ex instanceof MissingRequestHeaderException) {
            MissingRequestHeaderException exception = (MissingRequestHeaderException) ex;
            String headerName = exception.getHeaderName();
            msg = "Required header '" + headerName + "' is missing.";
        }
        if (ex instanceof HttpMessageNotReadableException) {
            msg = ex.getMessage();
        }
        return msg;
    }
}

