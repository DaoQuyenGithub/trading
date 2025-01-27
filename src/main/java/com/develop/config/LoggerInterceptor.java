package com.develop.config;

import com.develop.config.restful.servletwrapper.MultipleReadHttpRequest;
import com.develop.config.restful.servletwrapper.MultipleReadHttpResponse;
import com.develop.constant.AppConstants;
import com.develop.log.LogContext;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
public class LoggerInterceptor  implements HandlerInterceptor {
    private final Tracer tracer;
    private final Environment environment;

    /**
     * Executed before actual handler is executed
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, final Object handler) {
        String traceId = tracer.currentSpan() != null ? tracer.currentSpan().context().traceId() : "";
        if (request instanceof MultipleReadHttpRequest) {
            LogContext.getCurrent().addServletRequest((MultipleReadHttpRequest) request,
                    traceId,
                    environment.getProperty(AppConstants.APPLICATION_NAME)
            );
        }
        return true;
    }

    /**
     * Executed before after handler is executed
     **/
    @Override
    public void postHandle(final HttpServletRequest request,
                           final HttpServletResponse response,
                           final Object handler,
                           final ModelAndView modelAndView) {
        response.setCharacterEncoding("UTF-8");
    }

    /**
     * Executed after complete request is finished
     **/
    @Override
    public void afterCompletion(final HttpServletRequest request,
                                final HttpServletResponse response,
                                final Object handler,
                                final Exception ex) {
        if (response instanceof MultipleReadHttpResponse) {
            LogContext.getCurrent().addServletResponse((MultipleReadHttpResponse)response);
        }
        LogContext.print();
    }
}
