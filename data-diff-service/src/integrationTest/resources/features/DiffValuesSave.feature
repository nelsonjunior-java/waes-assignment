# language: en
  # encoding: UTF-8

Feature: Save an encoded value

#  Scenario: Saving a left encoded value successfully
#    Given I save a left encoded value on the application endpoint
#    Then the operation is well succeed returning the http code 200
#    And the response contains the text WAITING_EVALUATION
#
#  Scenario: Saving a left encoded value using an String value as id
#    Given I save a left encoded value on the application endpoint using a non numerical id value as 'one'
#    Then the operation fails returning the http code 400
#
#  Scenario: Saving a left encoded value using an empty encoded value in the body request
#    Given I save a left encoded value using an empty value on the body property base64EncodedValue
#    Then the operation fails returning the http code 400
#
#  Scenario: Saving a left value using an parameter in the body request containing only empty spaces
#    Given I save a left value using in the body a base64EncodedValue property containing only empty spaces
#    Then the operation fails returning the http code 400
#
#  Scenario: Saving a left value using a not base 64 encoded value in the body request
#    Given I save a left value using a not base64 encoded value like '#not an base64 encoded value!!!'
#    Then the operation fails returning the http code 400
#    And the response contains the text Value must be in base64 format.
#
#  Scenario: Saving a right encoded value successfully
#    Given I save a right encoded value on the application endpoint
#    Then the operation is well succeed returning the http code 200
#    And the response contains the text WAITING_EVALUATION
#
#  Scenario: Saving a right encoded value using an String value as id
#    Given I save a left encoded value on the application endpoint using a non numerical id value as 'one'
#    Then the operation fails returning the http code 400
#
#  Scenario: Saving a right encoded value using an empty encoded value in the body request
#    Given I save a right encoded value using an empty value on the body property base64EncodedValue
#    Then the operation fails returning the http code 400
#
#  Scenario: Saving a right value using an parameter in the body request containing only empty spaces
#    Given I save a right value using in the body a base64EncodedValue property containing only empty spaces
#    Then the operation fails returning the http code 400
#
#  Scenario: Saving a right value using a not base 64 encoded value in the body request
#    Given I save a right value using a not base64 encoded value like '#not an base64 encoded value!!!'
#    Then the operation fails returning the http code 400
#    And the response contains the text Value must be in base64 format.