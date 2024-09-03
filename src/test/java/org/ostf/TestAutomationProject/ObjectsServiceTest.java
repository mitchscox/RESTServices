package org.ostf.TestAutomationProject;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ObjectsServiceTest {
    Logger logger = LogManager.getLogger();
    private ObjectsService objectsService;

    @BeforeClass
    public void setup() {
        objectsService = new ObjectsService();
    }
    // TODO remove additional assertion comments when done with validation logic
    // TODO configure hardcoding of string ids from 1 to phone object model
    @Test
    public void testGetAllObjects() {
        Response response = objectsService.getAllObjects();
        logger.info("Object Data:"+response);
        Assert.assertEquals(response.getStatusCode(), 200);

        // Additional assertions based on response content
    }



    @Test
    public void testGetObjectById() {
        String id = "1"; // Replace with a valid ID
        Response response = objectsService.getObjectById(id);
        Assert.assertEquals(response.getStatusCode(), 200);
        // Additional assertions based on response content
    }
    /*
    @Test
    public void testCreateObject() {
        // Create a sample object to post
        String sampleObject = "{ \"name\": \"New Object\", \"data\": { \"property\": \"value\" }}";
        Response response = objectsService.createObject(sampleObject);
        Assert.assertEquals(response.getStatusCode(), 201);
        // Additional assertions based on response content
    }

    // TODO while not relevant update properties to object model phone for updating
    // TODO also again make the ID field configurable
    @Test
    public void testUpdateObject() {
        String id = "1";

        String updatedObject = "{ \"name\": \"Updated Object\", \"data\": { \"property\": \"new value\" }}";
        Response response = objectsService.updateObject(id, updatedObject);
        Assert.assertEquals(response.getStatusCode(), 200);
        // Additional assertions based on response content
    }

    @Test
    public void testDeleteObject() {
        String id = "1"; // Replace with a valid ID
        Response response = objectsService.deleteObject(id);
        Assert.assertEquals(response.getStatusCode(), 200);
        // Additional assertions based on response content
    }

     */
}