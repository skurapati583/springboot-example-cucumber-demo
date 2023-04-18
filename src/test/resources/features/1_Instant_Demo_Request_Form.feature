Feature: Instant Demo Request Form

  Background:
    Given User launches PHP Travels website

  Scenario: Submit Form - Positive
    Given User sees Instant Demo Request Form
    When User fills Instant Demo Request Form
      | First Name    | Karan          |
      | Last Name     | Arjun          |
      | Business Name | KARA           |
      | Email         | kara@gmail.com |
    But User answers correct captcha number
    And User submits form
    Then User sees Thank You message of form acknowledgement

  Scenario: Submit Form - Negative
    Given User sees Instant Demo Request Form
    When User submits form
    Then User sees an alert with message "Please type your first name"
    When User closes the alert
    And User fills Instant Demo Request Form
      | First Name    | Karan          |
    And User submits form
    Then User sees an alert with message "Please type your last name"
    When User closes the alert
    And User fills Instant Demo Request Form
      | Last Name    | Arjun          |
    And User submits form
    Then User sees an alert with message "Please type your business name"
    When User closes the alert
    And User fills Instant Demo Request Form
      | Business Name    | KARA          |
    And User submits form
    Then User sees an alert with message "Please type your email name"
    When User closes the alert