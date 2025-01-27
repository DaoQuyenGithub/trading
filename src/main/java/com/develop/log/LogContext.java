package com.develop.log;

public class LogContext {
    private static final ThreadLocal<LogContextModel> contextThreadLocal = new ThreadLocal<>();

    public static LogContextModel getCurrent() {
        LogContextModel context = contextThreadLocal.get();
        if (context == null) {
            context = LogContextModel.builder().build();
            contextThreadLocal.set(context);
        }
        return context;
    }

    public static void addTrace(String key, Object value) {
        getCurrent().addTrace(key, value);
    }

    public static void addException(Exception e) {
        getCurrent().addException(e);
    }

    public static void print() {
        contextThreadLocal.remove();
    }

    public static void init(LogContextModel model) {
        contextThreadLocal.set(model);
    }

}
