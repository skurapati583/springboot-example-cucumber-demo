@Registration
Feature: PHP Travels - Registration Form

  Background:
    Given User launches PHP Travels website

  Scenario: Registration Form - Positive
    When User chooses for Sign Up
    Then User sees Registration form launched in a separate tab
    When User enters Personal Information
      | First Name   | Karan          |
      | Last Name    | Arjun          |
      | Email        | KARA@gmail.com |
      | Phone Number | 8397362817     |
    And User enters Billing Address
      | Company Name     | Thoughtstorm       |
      | Street Address   | 10 Northland Trail |
      | Street Address 2 | Aberg Place        |
      | City             | London             |
      | State            | Dalarna            |
      | Postcode         | 141662             |
    And User enters Additional Information
      | Mobile | 524-265-0167 |
    And User chooses for automatic password generation
    Then User sees password strength meter is green
    When User submits the registration form


