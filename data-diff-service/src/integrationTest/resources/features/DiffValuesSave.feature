# language: en
  # encoding: UTF-8

Feature: Save a Left encoded value

  Scenario: Saving a left encoded value successfully
    Given I want to save a left encoded value on the application endpoint
    Then the operation is well succeed returning a http code 200
    And the response contains the text WAITING_EVALUATION