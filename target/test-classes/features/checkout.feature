Feature: Ecommerce Checkout

  Scenario: Complete checkout successfully

    Given user is logged into the ecommerce application
    When user adds Sauce Labs Backpack to the cart
    And user opens the cart
    And user proceeds to checkout
    And user enters customer details
    And user completes the order
    Then order confirmation should be displayed