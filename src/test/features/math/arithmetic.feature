Feature: Basic Arithmetic

  As an user I want to add and multiply numbers.

  Scenario: Simple addition
    This scenario show the the basic use of additions.

    When I add 4 and 5
    Then the result is 9

  Scenario Outline: Multiple additions
    When I add <summand1> and <summand2>
    Then the sum is <sum>

  Examples:
    | summand1 | summand2 | sum |
    | 1        | 2        | 3   |
    | 5        | -5       | 0   |
    | 100      | -110     | -10 |