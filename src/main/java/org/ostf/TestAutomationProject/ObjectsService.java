package org.ostf.TestAutomationProject;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ObjectsService {
    Logger logger = LogManager.getLogger();
    // TODO below endpoint needs to go into application.properties
    private final String endpoint = "objects";
    private final BaseService baseService;
    private final ObjectMapper objectMapper;

    //@Autowired
    // autowiring unnecessary as declaring a constructor
    public ObjectsService(BaseService baseService, ObjectMapper objectMapper) {
        this.baseService = baseService;
        this.objectMapper = objectMapper;

        try {
            String response = baseService.getRequest(endpoint).body().asString();
            Product[] products = objectMapper.readValue(response, Product[].class);
            logger.info("Object Service set created");
        } catch (JsonProcessingException e) {
            logger.error("Failed to parse json: ", e);
        }
    }

    public Response getAllObjects() {
        return baseService.getRequest(endpoint);
    }

    public Response getObjectById(String id) {
        return baseService.getRequest(endpoint + "/" + id);
    }

    public Response createObject(Product product) {
        try {
            String productJson = objectMapper.writeValueAsString(product);
            return baseService.postRequest(endpoint, productJson);
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize Product object", e);
            return null; // This could be handled better
        }
    }

    public Response updateObject(String id, Product product) {
        try {
            String productJson = objectMapper.writeValueAsString(product);
            return baseService.putRequest(endpoint + "/" + id, productJson);
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize Product object", e);
            return null; // This too same as above
        }
    }

    public Response deleteObject(String id) {
        return baseService.deleteRequest(endpoint + "/" + id);
    }
}
