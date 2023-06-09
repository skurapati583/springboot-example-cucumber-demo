package com.cucumber.springboottwo.example.library;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ThreadGuard;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "browsers.default-driver", havingValue = "chrome")
@Scope("prototype")
public class ChromeDriverImpl implements DriverManager {

    @Override
    public void initializeWebDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        DRIVER_THREAD_LOCAL.set(ThreadGuard.protect(new ChromeDriver(chromeOptions)));
    }
}
