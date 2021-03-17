# language: en
  # encoding: UTF-8

Feature: Decode a base64 value

  Scenario: Decoding a value successfully
    Given I try to decode a base64 encode value like 'TXkgZW5jb2RlZCB2YWx1ZSE='
    Then the operation is well succeed returning the http code 200
    And the response contains the text My encoded value!

  Scenario: Decoding an empty value
    Given I try to decode a value passing in the decodeValue body parameter an empty value
    Then the operation fails returning the http code 400

  Scenario: Decoding a value with only whitespaces
    Given I try to decode a value passing in the decodeValue body parameter with only white spaces
    Then the operation fails returning the http code 400

  Scenario: Decoding a value that is not in the base64 format
    Given I try to decode a value passing in the decodeValue body parameter a not base64 value
    Then the operation fails returning the http code 400
    And the response contains the text Value must be in base64 format.

