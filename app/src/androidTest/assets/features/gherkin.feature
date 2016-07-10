@foo
Feature: Gherkin Showcase

  Scenario: With Integer as parameter
    Given I have 5 cukes in my belly
    Then I have an awesome test

  Scenario: With String as parameter
    Given I enter username and password
    Then I have an awesome test

  Scenario: With DataTable as parameter
    Given I have some employees
      | id | firstName | lastName   |
      | 1  | Tobias    | Heine      |
      | 2  | Max       | Mustermann |
    Then I have an awesome test

  Scenario: With List of Strings from DataTable as parameter
    Given the following animals in this table:
      | cow   |
      | horse |
      | sheep |
    Then I have an awesome test

  Scenario: With Date as parameter
    Given I want to transform this 06/27/2007 to a date
    Then I have an awesome test

