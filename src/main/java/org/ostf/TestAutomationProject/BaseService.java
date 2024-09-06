package org.ostf.TestAutomationProject;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class BaseService {

    Logger logger = LogManager.getLogger();

    public BaseService(@Value("${external.api.url}") String apiUrl) {
        RestAssured.baseURI = apiUrl;
        logger.info(" Base Service created with URL = " +apiUrl);
    }
    public Response getRequest(String endpoint) {
        return RestAssured.get(endpoint);
    }

    public Response postRequest(String endpoint, Object body) {
        return RestAssured.given()
                .contentType("application/json")
                .body(body)
                .post(endpoint);
    }

    public Response putRequest(String endpoint, Object body) {
        return RestAssured.given()
                .contentType("application/json")
                .body(body)
                .put(endpoint);
    }

    public Response deleteRequest(String endpoint) {
        return RestAssured.delete(endpoint);
    }
}