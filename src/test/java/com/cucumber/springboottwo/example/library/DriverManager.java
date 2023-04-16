package com.cucumber.springboottwo.example.library;

import com.google.common.base.Preconditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public interface DriverManager {

    ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    void initializeWebDriver();

    default WebDriver getWebDriver() {
        Preconditions.checkNotNull(DRIVER_THREAD_LOCAL.get(), "WebDriver Instance is null");
        return DRIVER_THREAD_LOCAL.get();
    }

    default void terminateWebDriver() {
        if (Objects.nonNull(DRIVER_THREAD_LOCAL.get())) {
            DRIVER_THREAD_LOCAL.get().quit();
            DRIVER_THREAD_LOCAL.remove();
        }
    }

    default WebDriverWait getWebDriverWait() {
        return new WebDriverWait(getWebDriver(), Duration.ofSeconds(25));
    }

}
