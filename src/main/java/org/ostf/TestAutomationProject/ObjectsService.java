package org.ostf.TestAutomationProject;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ObjectsService {

    Logger logger = LogManager.getLogger();

    // Inject the value from application.properties
    @Value("${objects.api.endpoint}")
    private String endpoint;

    private final BaseService baseService;

    // Constructor to initialize BaseService
    public ObjectsService(BaseService baseService) {
        this.baseService = baseService;
        logger.info("Object Service Created ");
    }

    public Response getAllObjects() {
        return baseService.getRequest(endpoint);
    }

    public Response getObjectById(String id) {
        return baseService.getRequest(endpoint + "/" + id);
    }

    public Response createObject(Object body) {
        return baseService.postRequest(endpoint, body);
    }

    public Response updateObject(String id, Object body) {
        return baseService.putRequest(endpoint + "/" + id, body);
    }

    public Response deleteObject(String id) {
        return baseService.deleteRequest(endpoint + "/" + id);
    }
}
