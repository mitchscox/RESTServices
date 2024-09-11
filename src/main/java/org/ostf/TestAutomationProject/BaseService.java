package org.ostf.TestAutomationProject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


public class BaseService {

    Logger logger = LogManager.getLogger();

    private final String apiUrl;


    public BaseService(@Value("${external.api.url}") String apiUrl) {
        this.apiUrl = apiUrl;
        RestAssured.baseURI = apiUrl;  // Set RestAssured base URL
        logger.info("Base Service instance created with base URL = {}", apiUrl);
    }


    public Response getRequest(String endpoint) {
        logger.info("Making GET request to endpoint: {}", apiUrl + endpoint);
        return RestAssured.get(apiUrl + endpoint);  // Ensure correct URL construction
    }


    public Response postRequest(String endpoint, Object body) {
        logger.info("Making POST request to endpoint: {}", apiUrl + endpoint);
        return RestAssured.given()
                .contentType("application/json")
                .body(body)
                .post(apiUrl + endpoint);
    }


    public Response putRequest(String endpoint, Object body) {
        logger.info("Making PUT request to endpoint: {}", apiUrl + endpoint);
        return RestAssured.given()
                .contentType("application/json")
                .body(body)
                .put(apiUrl + endpoint);
    }


    public Response deleteRequest(String endpoint) {
        logger.info("Making DELETE request to endpoint: {}", apiUrl + endpoint);
        return RestAssured.delete(apiUrl + endpoint);
    }
}
