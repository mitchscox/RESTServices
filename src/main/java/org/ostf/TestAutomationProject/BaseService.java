package org.ostf.TestAutomationProject;


import io.restassured.RestAssured;
import io.restassured.response.Response;


public class BaseService {
    /* TODO make endpoint URl configurable https://api.restful-api.dev/ vs http://localhost:80/ */

    private final String baseUrl = "https://api.restful-api.dev/";
    //private final String baseUrl = "http://localhost:80/";
    public BaseService() {
        RestAssured.baseURI = baseUrl;
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