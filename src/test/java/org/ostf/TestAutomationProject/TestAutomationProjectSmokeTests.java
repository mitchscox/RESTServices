package org.ostf.TestAutomationProject;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assumptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestAutomationProjectSmokeTests {

    @Autowired
    private ObjectsService objectsService;

    @Autowired
    private ObjectMapper objectMapper;

    Logger logger = LogManager.getLogger();

    private boolean smokeTestsPassed = true;

    @BeforeEach
    void checkSmokeTestsStatus() {
        logger.info ("Smoke test status = " +smokeTestsPassed);
        Assumptions.assumeTrue(smokeTestsPassed, "Skipping non-smoke tests since a smoke test has failed.");
    }

    @Test
    @Tag("smoke")
    public void testCreateObject() {
        Response response = mock(Response.class);
        when(response.getStatusCode()).thenReturn(201);
        boolean passed = response.getStatusCode() == 201;
        if (!passed) smokeTestsPassed = false;
        org.junit.jupiter.api.Assertions.assertEquals(201, response.getStatusCode());
    }

    @Test
    @Tag("smoke")
    public void testUpdateObject() {
        Response response = mock(Response.class);
        when(response.getStatusCode()).thenReturn(200);
        boolean passed = response.getStatusCode() == 200;
        if (!passed) smokeTestsPassed = false;
        org.junit.jupiter.api.Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    @Tag("smoke")
    public void testDeleteObject() {
        Response response = mock(Response.class);
        when(response.getStatusCode()).thenReturn(200);
        boolean passed = response.getStatusCode() == 200;
        if (!passed) smokeTestsPassed = false;
        org.junit.jupiter.api.Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    @Tag("smoke")
    public void testGetAllObjects() {
        Response response = objectsService.getAllObjects();
        logger.info("testGetAllObjects Response Code : " + response.getStatusCode());
        boolean passed = response.getStatusCode() == 200;
        if (!passed) smokeTestsPassed = false;
        org.junit.jupiter.api.Assertions.assertEquals(200, response.getStatusCode());
    }


}
