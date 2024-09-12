package com.inditex.prices_service.cucumber;

import com.inditex.prices_service.model.PricesEntity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

public class PricesSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<PricesEntity> response;
    private String url;

    @Given("the productId is {int}, brandId is {int}, and applicationDate is {string}")
    public void setupRequestParameters(int productId, int brandId, String applicationDate) {
        url = String.format("/api/prices/search?productId=%d&brandId=%d&applicationDate=%s", productId, brandId, applicationDate);
    }

    @When("the client sends a GET request to the price endpoint")
    public void sendRequest() {
        response = restTemplate.getForEntity(url, PricesEntity.class);
    }

    @Then("the response priceList should be {int} and the price should be {double}")
    public void validateResponse(int expectedPriceList, double expectedPrice) {
        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedPriceList, response.getBody().getPriceList());
        Assertions.assertEquals(expectedPrice, response.getBody().getPrice());
    }

}
