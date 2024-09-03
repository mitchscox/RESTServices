package org.ostf.TestAutomationProject;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        logger.debug("Response Code : "+ response.getStatusCode());
        logger.debug("Object Data Type : " + response.getContentType());

        // why is this below logger statement printing to info and above?
        // logger.debug("Data body : " + response.body().prettyPrint());
        Assert.assertEquals(response.getStatusCode(), 200);

    }



    @Test
    public void testGetAllApplePhones() {
        List<Map<String, Object>> jsonData = loadJsonData();
        List<String> applePhoneNames = jsonData.stream()
                .map(obj -> (String) obj.get("name"))
                // TODO this filter needs to be added to Phone Object Model
                .filter(name -> name.startsWith("Apple iPhone"))
                .toList();

        applePhoneNames.forEach(name -> logger.info("Apple Phone: {}", name));
        Assert.assertFalse(applePhoneNames.isEmpty(), "There should be at least one Apple phone.");
    }


    private List<Map<String, Object>> loadJsonData() {
        Response response = objectsService.getAllObjects();
        List<Map<String, Object>> jsonData = response.jsonPath().getList("$");
        return jsonData;
    }
    @Test
    public void testGetPhoneWithLowestPrice() {
        // Get the response
        Response response = objectsService.getAllObjects();

        // Convert response to JSON data
        List<Map<String, Object>> jsonData = response.jsonPath().getList("$");

        Optional<String> lowestPricePhone = jsonData.stream()
                .filter(obj -> obj.get("data") != null && ((Map<String, Object>) obj.get("data")).get("price") != null)
                .min(Comparator.comparing(obj -> ((Number) ((Map<String, Object>) obj.get("data")).get("price")).doubleValue()))
                .map(obj -> (String) obj.get("name"));

        lowestPricePhone.ifPresent(name -> logger.info("Lowest Price Device: {}", name));

        Assert.assertTrue(lowestPricePhone.isPresent(), "There should be at least one phone with a price.");
    }

    /*
    @Test
    public void testGetObjectById() {
        String id = "1"; // Replace with a valid ID
        Response response = objectsService.getObjectById(id);
        Assert.assertEquals(response.getStatusCode(), 200);
        // Additional assertions based on response content
    }

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