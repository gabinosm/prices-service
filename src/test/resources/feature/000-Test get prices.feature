Feature: Get price with product, brand, and application date

  @Feature1
  Scenario: Request at 10:00 on June 14 for product 35455 and brand 1 (ZARA)
    Given the productId is 35455, brandId is 1, and applicationDate is "2020-06-14T10:00:00"
    When the client sends a GET request to the price endpoint
    Then the response priceList should be 1 and the price should be 35.50

  Scenario: Request at 16:00 on June 14 for product 35455 and brand 1 (ZARA)
    Given the productId is 35455, brandId is 1, and applicationDate is "2020-06-14T16:00:00"
    When the client sends a GET request to the price endpoint
    Then the response priceList should be 2 and the price should be 25.45

  Scenario: Request at 21:00 on June 14 for product 35455 and brand 1 (ZARA)
    Given the productId is 35455, brandId is 1, and applicationDate is "2020-06-14T21:00:00"
    When the client sends a GET request to the price endpoint
    Then the response priceList should be 1 and the price should be 35.50

  Scenario: Request at 10:00 on June 15 for product 35455 and brand 1 (ZARA)
    Given the productId is 35455, brandId is 1, and applicationDate is "2020-06-15T10:00:00"
    When the client sends a GET request to the price endpoint
    Then the response priceList should be 3 and the price should be 30.50

  Scenario: Request at 21:00 on June 16 for product 35455 and brand 1 (ZARA)
    Given the productId is 35455, brandId is 1, and applicationDate is "2020-06-16T21:00:00"
    When the client sends a GET request to the price endpoint
    Then the response priceList should be 4 and the price should be 38.95