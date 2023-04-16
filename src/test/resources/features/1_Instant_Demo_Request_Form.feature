Feature: Instant Demo Request Form2

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