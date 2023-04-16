package com.cucumber.springboottwo.example.pages;

import com.cucumber.springboottwo.example.library.ElementActions;
import com.cucumber.springboottwo.example.model.Locators;
import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InstantDemoRequestForm {

    @Autowired
    ElementActions elementActions;

    @Autowired
    Locators locators;

    @Value("${php_travels.url}")
    private String phpTravelsURL;

    public void launchPHPTravelsApp() {
        elementActions.launch(phpTravelsURL);
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
    }

    public void enterCaptchaNumber() {
        int firstNumber = Integer.parseInt(
                elementActions.getElementText(
                        locators.getInstantDemoRequestFormCaptchaFirstNumber()));
        int secondNumber = Integer.parseInt(
                elementActions.getElementText(
                        locators.getInstantDemoRequestFormCaptchaSecondNumber()));
        elementActions.enterTextIntoInputTextBox(locators.getInstantDemoRequestFormCaptchaInputField(),
                String.valueOf(firstNumber + secondNumber));
    }

    public void submitForm() {
        elementActions.clickButton(locators.getInstantDemoRequestFormSubmitButton());
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
        ) ;

        return successCheck && successMessageCheck && successMessageDescriptionCheck;
    }

}
