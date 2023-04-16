package com.cucumber.springboottwo.example.steps;

import com.cucumber.springboottwo.example.library.DriverManager;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

public class HookSteps implements En {

    @Autowired
    DriverManager driverManager;

    public HookSteps() {
        Before(() -> {
            driverManager.initializeWebDriver();
        });

        After(() -> {
            driverManager.terminateWebDriver();
        });

        AfterStep((Scenario scenario) -> {
            byte[] screenshot = ((TakesScreenshot) driverManager.getWebDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "attachment-"+ LocalTime.now().toSecondOfDay());
        });

    }
}
