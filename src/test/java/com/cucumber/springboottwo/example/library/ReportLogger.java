package com.cucumber.springboottwo.example.library;

import java.util.ArrayList;
import java.util.List;

public enum ReportLogger {

    INSTANCE;

    private static final ThreadLocal<List<String>> LOGGER_GROUP = new ThreadLocal<>();

    public void resetLog() {
        LOGGER_GROUP.remove();
        LOGGER_GROUP.set(new ArrayList<>());
    }

    public void logMessage(String message) {
        LOGGER_GROUP.get().add(message);
    }

    public List<String> getLogs() {
        return LOGGER_GROUP.get();
    }

}
