package org.ostf.TestAutomationProject;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//import javax.annotation.PostConstruct;

@Service
public class ObjectsService {

    Logger logger = LogManager.getLogger();

    @Value("${objects.api.endpoint}")
    private String endpoint;

    private final BaseService baseService;

    // Constructor that injects BaseService
    public ObjectsService(BaseService baseService) {
        this.baseService = baseService;
    }

    // Use @PostConstruct to log after the endpoint has been set by Spring
    /* @PostConstruct
    public void init() {
        logger.info("Object Service Created with endpoint: {}", endpoint);
    }
    */
    // Method to fetch all objects
    public Response getAllObjects() {
        logger.info("Fetching all objects from endpoint: {}", endpoint);
        return baseService.getRequest(endpoint);
    }

    // Method to fetch an object by ID
    public Response getObjectById(String id) {
        logger.info("Fetching object by ID: {} from endpoint: {}", id, endpoint);
        return baseService.getRequest(endpoint + "/" + id);
    }

    // Method to create an object
    public Response createObject(Object body) {
        logger.info("Creating object at endpoint: {}", endpoint);
        return baseService.postRequest(endpoint, body);
    }

    // Method to update an object
    public Response updateObject(String id, Object body) {
        logger.info("Updating object with ID: {} at endpoint: {}", id, endpoint);
        return baseService.putRequest(endpoint + "/" + id, body);
    }

    // Method to delete an object
    public Response deleteObject(String id) {
        logger.info("Deleting object with ID: {} from endpoint: {}", id, endpoint);
        return baseService.deleteRequest(endpoint + "/" + id);
    }
}
