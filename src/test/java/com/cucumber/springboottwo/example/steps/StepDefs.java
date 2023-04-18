package com.cucumber.springboottwo.example.steps;

import com.cucumber.springboottwo.example.pages.InstantDemoRequestForm;
import com.cucumber.springboottwo.example.pages.RegistrationPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class StepDefs implements En {

    @Autowired
    InstantDemoRequestForm instantDemoRequestForm;

    @Autowired
    RegistrationPage registrationPage;

    public StepDefs() {

        Given("User launches PHP Travels website", () -> {
            instantDemoRequestForm.launchPHPTravelsApp();
        });

        Given("User sees Instant Demo Request Form", () -> {
            assert instantDemoRequestForm.isInstantDemoRequestFormVisible();
        });

        When("User fills Instant Demo Request Form", (DataTable formDetails) -> {
            var detailsMap = formDetails.asMap();

            for (Map.Entry<String, String> detail: detailsMap.entrySet()) {
                instantDemoRequestForm.enterRequestFormField(detail.getKey(), detail.getValue());
            }
        });

        When("User answers correct captcha number", () -> {
            instantDemoRequestForm.enterCaptchaNumber();
        });

        When("User submits form", () -> {
            instantDemoRequestForm.submitForm();
        });

        Then("User sees Thank You message of form acknowledgement", () -> {
            assert instantDemoRequestForm.isSubmissionSuccessful();
        });

        Then("User sees an alert with message {string}", (String alertMessage) -> {
            instantDemoRequestForm.verifyAlertMessage(alertMessage);
        });

        When("User closes the alert", () -> {
            instantDemoRequestForm.closeAlert();
        });

        When("User chooses for Sign Up", () -> {
           instantDemoRequestForm.chooseSignUp();
        });

        Then("User sees Registration form launched in a separate tab", () -> {
           instantDemoRequestForm.switchToRegistrationTab();
        });

        When("User enters Personal Information", (DataTable info) -> {
            info.asMap().forEach((key, value) -> registrationPage.enterPersonalInfo(key, value));
        });

        When("User enters Billing Address", (DataTable info) -> {
            info.asMap().forEach((key, value) -> registrationPage.enterBillingAddress(key, value));
        });

        When("User enters Additional Information", (DataTable info) -> {
            info.asMap().forEach((key, value) -> registrationPage.enterAdditionalRequiredInfo(key, value));
        });

        When("User chooses for automatic password generation", () -> {
           registrationPage.enterPasswordAutomatically();
        });

        Then("User sees password strength meter is green", () -> {
           registrationPage.verifyPasswordStrengthMeterColor();
        });

        When("User submits the registration form", () -> {
           registrationPage.completeRegistration();
        });


    }
}
