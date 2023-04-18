package com.cucumber.springboottwo.example.pages;

import com.cucumber.springboottwo.example.library.Assertions;
import com.cucumber.springboottwo.example.library.ElementActions;
import com.cucumber.springboottwo.example.library.ReportLogger;
import com.cucumber.springboottwo.example.model.Locators;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.support.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@Slf4j
public class RegistrationPage {

    @Autowired
    ElementActions elementActions;

    @Autowired
    Locators locators;

    String threadName = Thread.currentThread().getName();

    public void enterPersonalInfo(String fieldName, String inputValue) {
        elementActions.enterTextIntoInputTextBox(
                locators.getRegistrationPageGenericInputTextBox().replace("%s", fieldName),
                inputValue
        );
        ReportLogger.INSTANCE.logMessage("Entered field name: " + fieldName + " and field value: " + inputValue);
    }

    public void enterBillingAddress(String fieldName, String inputValue) {
        elementActions.enterTextIntoInputTextBox(
                locators.getRegistrationPageGenericInputTextBox().replace("%s", fieldName),
                inputValue
        );
        ReportLogger.INSTANCE.logMessage("Entered field name: " + fieldName + " and field value: " + inputValue);
    }

    public void enterAdditionalRequiredInfo(String fieldName, String inputValue) {
        elementActions.enterTextIntoInputTextBox(
                locators.getRegistrationPageAddInfoMobileField(), inputValue
        );
        ReportLogger.INSTANCE.logMessage("Entered field name: " + fieldName + " and field value: " + inputValue);
    }

    public void enterPasswordAutomatically() {
        elementActions.clickButton(
                locators.getRegistrationPageGeneratePasswordBtn()
        );
        elementActions.clickButton(
                locators.getRegistrationPageSecurityModelCopyPasswordBtn()
        );
        ReportLogger.INSTANCE.logMessage("Entered automatic password.");
    }

    public void completeRegistration() {
        bypassCaptchaCheckbox();
        elementActions.clickButton(
                locators.getRegistrationPageRegisterBtn()
        );
        ReportLogger.INSTANCE.logMessage("Clicked on register button.");
    }

    public void verifyPasswordStrengthMeterColor() {
        Color actualColor = elementActions.getBackgroundColorOfElement(
                locators.getRegistrationPageSecurityPasswordStrengthMeter()
        );
        Color expectedColor = Color.fromString("#28a745");
        Assertions.checkEqual(expectedColor, actualColor);
    }

    public void bypassCaptchaCheckbox() {

        elementActions.switchIFrameByLocator(
                locators.getRegistrationPageCaptchaIframeTotal()
        );
        elementActions.clickButton(
                locators.getRegistrationPageCaptchaIframeCheckbox()
        );
        elementActions.switchToDefault();
    }


}
