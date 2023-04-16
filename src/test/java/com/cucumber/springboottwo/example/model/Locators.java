package com.cucumber.springboottwo.example.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:object_repository.properties")
@Getter @Setter
public class Locators {

    @Value("${phptravels.homepage.instant_demo_request_form.heading}")
    private String instantDemoRequestFormHeading;

    @Value("${phptravels.homepage.instant_demo_request_form.generic_input_field}")
    private String instantDemoRequestFormGenericInputField;

    @Value("${phptravels.homepage.instant_demo_request_form.captcha.first_number}")
    private String instantDemoRequestFormCaptchaFirstNumber;

    @Value("${phptravels.homepage.instant_demo_request_form.captcha.second_number}")
    private String instantDemoRequestFormCaptchaSecondNumber;

    @Value("${phptravels.homepage.instant_demo_request_form.captcha.input_field}")
    private String instantDemoRequestFormCaptchaInputField;

    @Value("${phptravels.homepage.instant_demo_request_form.submit_button}")
    private String instantDemoRequestFormSubmitButton;

    @Value("${phptravels.homepage.instant_demo_request_form.thank_you.success_icon}")
    private String thankYouPageSuccessIcon;

    @Value("${phptravels.homepage.instant_demo_request_form.thank_you.success_message}")
    private String thankYouPageSuccessMessage;

    @Value("${phptravels.homepage.instant_demo_request_form.thank_you.success_message_description}")
    private String thankYouPageSuccessDescription;


}
