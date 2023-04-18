package com.cucumber.springboottwo.example.pages;

import com.cucumber.springboottwo.example.library.ElementActions;
import com.cucumber.springboottwo.example.model.Locators;
import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InstantDemoRequestForm {

    @Autowired
    ElementActions elementActions;

    @Autowired
    Locators locators;

    String threadName = Thread.currentThread().getName();

    @Value("${php_travels.url}")
    private String phpTravelsURL;

    public void launchPHPTravelsApp() {
        elementActions.launch(phpTravelsURL);
        log.info("{}: Launched URL: {}", threadName, phpTravelsURL);
    }

    public boolean isInstantDemoRequestFormVisible() {

        return elementActions.getElementFromObjectRepository(
                locators.getInstantDemoRequestFormHeading()
        ) != null;
    }

    public void enterRequestFormField(String fieldName, String fieldValue) {
        String output = CaseFormat.UPPER_CAMEL.to(
                CaseFormat.LOWER_UNDERSCORE, CharMatcher.whitespace().removeFrom(fieldName));
        elementActions.enterTextIntoInputTextBox(
                locators.getInstantDemoRequestFormGenericInputField().replaceFirst(
                        "%s", output),
                fieldValue);
        log.info("{}: Entered field {}: {}", threadName, fieldName, fieldValue);
    }

    public void enterCaptchaNumber() {
        int firstNumber = Integer.parseInt(
                elementActions.getElementText(
                        locators.getInstantDemoRequestFormCaptchaFirstNumber()));
        int secondNumber = Integer.parseInt(
                elementActions.getElementText(
                        locators.getInstantDemoRequestFormCaptchaSecondNumber()));
        int result = firstNumber + secondNumber;
        elementActions.enterTextIntoInputTextBox(locators.getInstantDemoRequestFormCaptchaInputField(),
                String.valueOf(result));
        log.info("{}: Entering captcha number {} + {} = {}", threadName, firstNumber, secondNumber, result);
    }

    public void submitForm() {
        elementActions.clickButton(locators.getInstantDemoRequestFormSubmitButton());
        log.info("{}: Submit button clicked.", threadName);
    }

    public boolean isSubmissionSuccessful() {

        boolean successCheck =
                elementActions.getElementFromObjectRepository(
                        locators.getThankYouPageSuccessIcon()) != null;

        boolean successMessageCheck = elementActions.isTextPresentInsideElement(
           locators.getThankYouPageSuccessMessage(), "Thank you!"
        ) ;

        boolean successMessageDescriptionCheck = elementActions.isTextPresentInsideElement(
                locators.getThankYouPageSuccessDescription(),
                "We have sent your demo credentials to your email please check your email to test demo website. " +
                        "if message not found inbox please check spam folder"
        );

        if(successCheck) log.info("{}: Form submitted successfully.", threadName);
        else log.error("{}: Form not submitted successfully.", threadName);

        if(successMessageCheck) log.info("{}: Successful submission message displayed.", threadName);
        else log.error("{}: Successful submission message is not displayed.", threadName);

        if(successMessageDescriptionCheck) log.info("{}: Successful submission message description displayed.", threadName);
        else log.error("{}: Successful submission message is description displayed.", threadName);

        return successCheck && successMessageCheck && successMessageDescriptionCheck;
    }

    public void verifyAlertMessage(String message) {
        elementActions.isAlertRaisedWithTextMessage(message);
    }

    public void closeAlert() {
        elementActions.closeAlert();
    }

    public void chooseSignUp() {
        elementActions.clickButton(locators.getSignUpBtn());
    }

    public void switchToRegistrationTab() {
        elementActions.switchTab();
    }
}
