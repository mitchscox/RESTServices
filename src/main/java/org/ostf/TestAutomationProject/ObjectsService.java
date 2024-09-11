package org.ostf.TestAutomationProject;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ObjectsService {

    Logger logger = LogManager.getLogger();

    private final String endpoint;
    private final BaseService baseService;

    public ObjectsService(BaseService baseService, String endpoint) {
        this.baseService = baseService;
        this.endpoint = endpoint;
    }

    public void init() {
        logger.info("Object Service Created with endpoint: {}", endpoint);
    }

    public Response getAllObjects() {
        logger.info("Fetching all objects from endpoint: {}", endpoint);
        return baseService.getRequest(endpoint);
    }

    public Response getObjectById(String id) {
        logger.info("Fetching object by ID: {}", id);
        return baseService.getRequest(endpoint + "/" + id);
    }

    public Response createObject(Object body) {
        logger.info("Creating a new object at endpoint: {}", endpoint);
        return baseService.postRequest(endpoint, body);
    }

    public Response updateObject(String id, Object body) {
        logger.info("Updating object with ID: {}", id);
        return baseService.putRequest(endpoint + "/" + id, body);
    }

    public Response deleteObject(String id) {
        logger.info("Deleting object with ID: {}", id);
        return baseService.deleteRequest(endpoint + "/" + id);
    }
}
