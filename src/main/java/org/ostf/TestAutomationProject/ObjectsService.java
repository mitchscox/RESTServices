package org.ostf.TestAutomationProject;

import io.restassured.response.Response;

public class ObjectsService extends BaseService {

    // TODO configure objects vs objects.json
    private final String endpoint = "objects";

    public Response getAllObjects() {
        return getRequest(endpoint);
    }

    public Response getObjectById(String id) {
        return getRequest(endpoint + "/" + id);
    }

    public Response createObject(Object body) {
        return postRequest(endpoint, body);
    }

    public Response updateObject(String id, Object body) {
        return putRequest(endpoint + "/" + id, body);
    }

    public Response deleteObject(String id) {
        return deleteRequest(endpoint + "/" + id);
    }
}