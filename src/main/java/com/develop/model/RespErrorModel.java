package com.develop.model;

import com.develop.constant.AppConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespErrorModel {
    private String code;
    private String message;
    private String description;
    private String originalErrorCode;
    private String originalErrorDesc;
    private String traceId;
    private String level;

    @JsonFormat(pattern = AppConstants.DATETIME_FORMAT)
    private LocalDateTime serverDateTime;
    private String clientTransactionID;
    private String serverTransactionID;
    private String instance;
    private String link;
}
