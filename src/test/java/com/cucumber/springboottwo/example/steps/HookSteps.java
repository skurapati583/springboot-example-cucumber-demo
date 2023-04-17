package com.cucumber.springboottwo.example.steps;

import com.cucumber.springboottwo.example.library.DriverManager;
import com.cucumber.springboottwo.example.library.ReportLogger;
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
            driverManager.getWebDriver().manage().window().maximize();
        });

        After(() -> {
            driverManager.terminateWebDriver();
        });

        BeforeStep((Scenario scenario) -> {
            ReportLogger.INSTANCE.resetLog();
        });

        AfterStep((Scenario scenario) -> {
            try {
                byte[] screenshot = ((TakesScreenshot) driverManager.getWebDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "attachment-"+ LocalTime.now().toSecondOfDay());
            } catch (Exception e) {
                ReportLogger.INSTANCE.logMessage("Unable to capture screenshots.");
            }
            ReportLogger.INSTANCE.getLogs().forEach(scenario::log);
        });

    }
}
