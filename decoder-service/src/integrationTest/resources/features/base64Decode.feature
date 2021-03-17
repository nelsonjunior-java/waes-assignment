# language: en
  # encoding: UTF-8

Feature: Decode a base64 value

  Scenario: Decoding an empty value
    Given I try to decode a value passing in the decodeValue body parameter an empty value
