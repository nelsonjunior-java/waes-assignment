# language: en
  # encoding: UTF-8

Feature: Compare two values

  Scenario: Comparing two values using an String value as id
    Given I compare a left and a right value using a non numerical id value as 'my_value'
    Then the operation fails returning the http code 400

  Scenario: Comparing two values using an id of a record that does not exist
    Given I compare a left and a right value using an id that does not exist like '1234568'
    Then the operation fails returning the NOT FOUND http code 404

  Scenario: Comparing two values having only a left value saved
    Given I save a left encoded value on the application endpoint using the id 111
    Then I try to compare it in the comparison endpoint using the previous id 111
    And the operation fails returning the http code 400
    And the response contains the text The right value is missing.

  Scenario: Comparing two values having only a right value saved
    Given I save a right encoded value on the application endpoint using the id 222
    Then I try to compare it in the comparison endpoint using the previous id 222
    And the operation fails returning the http code 400
    And the response contains the text The left value is missing.

  Scenario: Comparing two equal values
    Given I save a left encoded value on the application endpoint using the id 333 and the encode value 'dGVzdGU='
    And I save a right encoded value on the application endpoint using the id 333 and the encode value 'dGVzdGU='
    Then I try to compare it in the comparison endpoint using the previous id 333
    And the operation is well succeed returning the http code 200
    And the response contains the text EQUAL

  Scenario: Comparing two values with different size
    Given I save a left encoded value on the application endpoint using the id 444 and the encode value 'dGVzdGU='
    And I save a right bigger encoded value on the application endpoint using the id 444 and the encode value 'dGVzdGUGRRSDE='
    Then I try to compare it in the comparison endpoint using the previous id 444
    And the operation is well succeed returning the http code 200
    And the response contains the text DIFFERENT_SIZE

  Scenario: Comparing two values with the same size but with different characters
    Given I save a left encoded value on the application endpoint using the id 555 and the encode value 'dGVzdGU='
    And I save a right encoded value with same size but with different characters on the application endpoint using the id 555 and the encode value 'TSGVzdGU='
    Then I try to compare it in the comparison endpoint using the previous id 555
    And the operation is well succeed returning the http code 200
    And the response contains the text SAME_SIZE_WITH_DIFFERENCES