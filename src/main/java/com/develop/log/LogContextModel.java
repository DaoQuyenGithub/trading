package com.develop.log;

import com.develop.config.restful.servletwrapper.MultipleReadHttpRequest;
import com.develop.config.restful.servletwrapper.MultipleReadHttpResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

@Data
@Builder
public class LogContextModel {

    private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
    private static final String DDMMYYYYHH24MISSZZZ = "dd/MM/yyyy HH:mm:ss.SSS";
    private static final String HH24MISSZZZ = "HH:mm:ss.SSS";

    private Pattern multilineMaskPattern;
    private Pattern multilineHiddenPattern;


    //    @Value("${plog.response:true}")
    @Builder.Default
    private boolean logResponse = true;
    //    @Value("${plog.header:true}")
    @Builder.Default
    private boolean logHeader = true;
    //    @Value("${plog.request:true}")
    @Builder.Default
    private boolean logRequest = true;
    //    @Value("${log.mask-patterns:}")
    @Builder.Default
    List<String> maskPatterns = new ArrayList<>();
    //    @Value("${log.hidden-patterns:}")
    @Builder.Default
    List<String> hiddenPatterns = new ArrayList<>();


    @Builder.Default
    private String respCode = "";
    private String requestId;
    private String requestTime;
    private String traceId;

    private String appName;
    private String httpStatus;

    @Builder.Default
    private Map<String, Object> header = new TreeMap<>();
    private JsonNode request;
    private JsonNode response;

    @Builder.Default
    private Map<String, Object> responseHeader = new LinkedHashMap<>();
    @Builder.Default
    private Map<String, Object> traces = new LinkedHashMap<>();
    @Builder.Default
    private List<Object> restTemplate = new ArrayList<>();
    @Builder.Default
    private Date start = new Date();
    private Date end;
    private Integer duration;
    private final List<Exception> exception = new ArrayList<>();

    public void addTrace(String key, Object value) {
        traces.put(key, value);
    }


    public void addException(Exception e) {
        exception.add(e);
    }

    @SneakyThrows
    public void addServletRequest(MultipleReadHttpRequest request, String traceId, String appName) {
        try {
            this.traceId = traceId;
            this.appName = appName;
            String reqString = new String(request.getCopy());
            if (!StringUtils.isEmpty(reqString)) {
                this.request = convertToJsonNode(reqString);
            }

            Enumeration<String> enumeration = request.getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                this.header.put(key, request.getHeader(key));
            }
        } catch (Exception e) {
            this.exception.add(e);
        }
    }

    @SneakyThrows
    public void addServletResponse(MultipleReadHttpResponse response) {
        try {
            this.httpStatus = String.valueOf(response.getStatus());

            String resString = new String(response.getCopy());
            if (!StringUtils.isEmpty(resString)) {
                this.response = convertToJsonNode(resString);
                if (this.response.has("code")) {
                    this.respCode = this.response.get("code").asText();
                }
            }

            Collection<String> headerNames = response.getHeaderNames();

            // Iterate over header names and get their values
            for (String headerName : headerNames) {
                String headerValue = response.getHeader(headerName);
                this.responseHeader.put(headerName, headerValue);
            }
        } catch (Exception e) {
            this.exception.add(e);
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private JsonNode castToJson() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        if (!Objects.isNull(respCode))
            json.putPOJO("respCode", respCode);
        if (!Objects.isNull(appName))
            json.putPOJO("appName", appName);
        if (!Objects.isNull(requestId))
            json.putPOJO("requestId", requestId);
        if (!Objects.isNull(traceId))
            json.putPOJO("traceId", traceId);
        if (!header.isEmpty())
            json.putPOJO("header", header);
        if (!Objects.isNull(request))
            json.putPOJO("request", request);
        if (!Objects.isNull(response))
            json.putPOJO("response", response);
        if (!traces.isEmpty())
            json.putPOJO("traces", traces);
        if (!restTemplate.isEmpty())
            json.putPOJO("restTemplate", restTemplate);
        if (!exception.isEmpty()) {
            ArrayNode arrayNode = json.putArray("exception");
            for (Exception e : exception) {
                ExceptionMessage em = new ExceptionMessage(e.getClass().getName(),
                        e.getStackTrace()[0].getLineNumber(),
                        e.getStackTrace()[0].getClassName(),
                        e.getMessage(),
                        getExceptionDetails(e));
                arrayNode.addPOJO(em);
            }
        }
        json.put("start", getDate(start));
        end = new Date();
        json.put("end", getDate(end));
        json.put("duration", end.getTime() - start.getTime());
        return json;
    }



    JsonNode convertToJsonNode(String string) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(string, JsonNode.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static ExceptionMessage getExceptionMessage(Exception e) {
        return new ExceptionMessage(e.getClass().getName(),
                e.getStackTrace()[0].getLineNumber(),
                e.getStackTrace()[0].getClassName(),
                e.getMessage(),
                getExceptionDetails(e));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExceptionMessage {
        String name;
        int lineNumber;
        String className;
        String message;
        List<String> details;
    }


    String getDate(Date date) {
        if (Objects.nonNull(date)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DDMMYYYYHH24MISSZZZ);
            simpleDateFormat.setTimeZone(TIME_ZONE);
            return simpleDateFormat.format(date);
        } else
            return null;
    }

    String getTime(Date date) {
        if (Objects.nonNull(date)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HH24MISSZZZ);
            simpleDateFormat.setTimeZone(TIME_ZONE);
            return simpleDateFormat.format(date);
        } else
            return null;
    }

    private static List<String> getExceptionDetails(Throwable ex) {
        List<String> lst = new ArrayList<>();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String stackTraceAsString = sw.toString();
        String[] lines = stackTraceAsString.split("\\r?\\n");
        for (String line : lines) {
            line = line.trim();
        }
        return lst;
    }



    public String getCurlNoBody(String url,
                           HttpMethod method,
                           HttpEntity<?> requestEntity,
                           Object... uriVariables) {
        try {
            StringBuilder curlCommand = new StringBuilder("curl -X ");
            curlCommand.append(method.name());
            if (requestEntity != null) {
                // Append headers
                requestEntity.getHeaders().forEach((headerName, headerValues) -> {
                    headerValues.forEach(headerValue -> {
                        curlCommand.append(" -H '").append(headerName).append(": ").append(headerValue).append("'");
                    });
                });
//                // Append body if present
//                if (requestEntity.getBody() != null) {
//                    curlCommand.append(" -d '").append(JsonUtils.toString(requestEntity.getBody())).append("'");
//                }
            }
            // Append the URL
            curlCommand.append(" '").append(url).append("'");
            // Append uriVariables if present
            if (uriVariables != null && uriVariables.length > 0) {
                for (Object uriVariable : uriVariables) {
                    curlCommand.append(" ").append(uriVariable.toString());
                }
            }
            // Log the curl command
            return curlCommand.toString();
        }catch (Exception e){
        }
        return url;
    }

    public static String generateCurlNobody(MultipleReadHttpRequest request) {
        StringBuilder curlCommand = new StringBuilder("curl -X ");

        // Get request method
        curlCommand.append(request.getMethod()).append(" ");

        // Get URL
        curlCommand.append(request.getRequestURL());

        // Get query string if present
        String queryString = request.getQueryString();
        if (queryString != null) {
            curlCommand.append("?").append(queryString);
        }

        // Add headers
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            curlCommand.append(" -H \"").append(headerName).append(": ").append(headerValue).append("\"");
        }

        // Add parameters if present (for POST requests)
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Enumeration<String> parameterNames = request.getParameterNames();
            StringBuilder postData = new StringBuilder();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                String paramValue = request.getParameter(paramName);
                if (postData.length() != 0) {
                    postData.append("&");
                }
                postData.append(paramName).append("=").append(paramValue);
            }
            if (postData.length() > 0) {
                curlCommand.append(" -d '").append(postData.toString()).append("'");
            }
        }

        return curlCommand.toString();
    }

}
