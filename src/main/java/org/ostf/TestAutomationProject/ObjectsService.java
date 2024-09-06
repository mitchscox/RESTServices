package org.ostf.TestAutomationProject;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectsService {
    Logger logger = LogManager.getLogger();
    private final String endpoint = "objects";
    private final BaseService baseService;

    @Autowired
    public ObjectsService(BaseService baseService) {
        this.baseService = baseService;
        logger.info("Object Service Data Set: " +baseService.getRequest(endpoint).body().print());
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
