package org.ostf.TestAutomationProject;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
class TestAutomationProjectApplicationTests {

	@Value("${external.api.url}")
	String url;

	@Autowired
	private ObjectsService objectsService;

	Logger logger = LogManager.getLogger();


	@BeforeTest
	void testPackInitialize() {
		logger.info("Beginning Test Pack Run");
		logger.info("URL = " + url);
	}
	/*
	@Test
	public void testCreateObject() {

		String sampleObject = "{ \"name\": \"New Object\", \"data\": { \"property\": \"value\" }}";
		Response response = objectsService.createObject(sampleObject);
		Assert.assertEquals(response.getStatusCode(), 201);

	}
    */
	@Test
	public void testGetAllObjects() {
		Response response = objectsService.getAllObjects();
		logger.info("testGetAllObjects Response Code : " + response.getStatusCode());
		logger.info("testGetAllObjects Object Data Type : " + response.getContentType());

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test
	public void testGetAllApplePhones() {
		List<Map<String, Object>> jsonData = loadJsonData();
		List<String> applePhoneNames = jsonData.stream()
				.map(obj -> (String) obj.get("name"))
				.filter(name -> name.startsWith("Apple iPhone"))
				.toList();

		applePhoneNames.forEach(name -> logger.info("Apple Phone: {}", name));
		Assert.assertFalse(applePhoneNames.isEmpty(), "There should be at least one Apple phone.");
	}

	private List<Map<String, Object>> loadJsonData() {
		Response response = objectsService.getAllObjects();
		return response.jsonPath().getList("$");
	}

	@Test
	public void testGetPhoneWithLowestPrice() {
		Response response = objectsService.getAllObjects();
		List<Map<String, Object>> jsonData = response.jsonPath().getList("$");

		Optional<String> lowestPricePhone = jsonData.stream()
				.filter(obj -> obj.get("data") != null && ((Map<String, Object>) obj.get("data")).get("price") != null)
				.min(Comparator.comparing(obj -> ((Number) ((Map<String, Object>) obj.get("data")).get("price")).doubleValue()))
				.map(obj -> (String) obj.get("name"));

		lowestPricePhone.ifPresent(name -> logger.info("Lowest Price Device: {}", name));
		Assert.assertTrue(lowestPricePhone.isPresent(), "There should be at least one phone with a price.");
	}

	@Test
	public void testAllIdsAreNotNull() {
		Response response = objectsService.getAllObjects();
		List<Map<String, Object>> jsonData = response.jsonPath().getList("$");

		boolean allIdsNotNull = jsonData.stream().allMatch(obj -> obj.get("id") != null);
		logger.info("All IDs are non-null: {}", allIdsNotNull);

		Assert.assertTrue(allIdsNotNull, "All ID fields should be non-null.");
	}
}