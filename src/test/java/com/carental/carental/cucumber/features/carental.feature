Feature: Rent a car
  Scenario: Successful car rental
    Given a car with registration number "ABC123" is available
    When I rent the car with registration number "ABC123"
    Then the car should no longer be available
