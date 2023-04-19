package com.cucumber.springboottwo.example.library;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ThreadGuard;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "browsers.default-driver", havingValue = "firefox")
@Scope("prototype")
public class FirefoxDriverImpl implements DriverManager {

    @Override
    public void initializeWebDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        DRIVER_THREAD_LOCAL.set(ThreadGuard.protect(new FirefoxDriver(firefoxOptions)));
    }
}
