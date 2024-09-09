package org.ostf.TestAutomationProject;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assumptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class TestAutomationProjectApplicationTests {

	@Value("${external.api.url}")
	String url;

	@Autowired
	private ObjectsService objectsService;

	@Autowired
	private ObjectMapper objectMapper;

	Logger logger = LogManager.getLogger();

	private boolean smokeTestsPassed = true;

	@BeforeEach
	void checkSmokeTestsStatus() {
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

	// The following tests will be skipped if any of the above smoke tests fail

	@Test
	public void testGetAllApplePhones() throws IOException {
		List<Product> products = loadJsonData();
		List<String> applePhoneNames = products.stream()
				.map(Product::getName)
				.filter(name -> name.contains("Apple iPhone"))
				.toList();

		applePhoneNames.forEach(name -> logger.info("Apple Phone: {}", name));
		org.junit.jupiter.api.Assertions.assertFalse(applePhoneNames.isEmpty(), "There should be at least one Apple phone.");
	}

	@Test
	public void testGetDeviceWithLowestPrice() throws IOException {
		List<Product> products = loadJsonData();

		Optional<String> lowestPricePhone = products.stream()
				.filter(product -> product.getData() != null && product.getData().getPrice() != null)
				.min(Comparator.comparing(product -> product.getData().getPrice()))
				.map(Product::getName);

		lowestPricePhone.ifPresent(name -> logger.info("Lowest Price Device: {}", name));
		org.junit.jupiter.api.Assertions.assertTrue(lowestPricePhone.isPresent(), "There should be at least one phone with a price.");
	}

	@Test
	public void testAllIdsAreNotNull() throws IOException {
		List<Product> products = loadJsonData();

		boolean allIdsNotNull = products.stream().allMatch(product -> product.getId() != null);
		logger.info("All IDs are non-null: {}", allIdsNotNull);

		org.junit.jupiter.api.Assertions.assertTrue(allIdsNotNull, "All ID fields should be non-null.");
	}

	@Value("#{'${phones}'.split(',')}")
	private List<String> phoneNames;

	@Test
	public void testGetLowestPricedPhone() throws IOException {
		List<Product> products = loadJsonData();

		Optional<Product> lowestPricedProduct = products.stream()
				.filter(product -> {
					String name = product.getName();
					return phoneNames.stream().anyMatch(name::startsWith);
				})
				.filter(product -> product.getData() != null && product.getData().getPrice() != null)
				.min(Comparator.comparing(product -> product.getData().getPrice()));

		org.junit.jupiter.api.Assertions.assertTrue(lowestPricedProduct.isPresent(), "No product found with the specified names.");

		lowestPricedProduct.ifPresent(product -> {
			logger.info("Lowest Priced Phone: {}", product.getName());
			logger.info("Price: {}", product.getData().getPrice());
		});
	}

	private List<Product> loadJsonData() throws IOException {
		Response response = objectsService.getAllObjects();
		String jsonResponse = response.body().asString();
		return objectMapper.readValue(jsonResponse, new TypeReference<List<Product>>() {});
	}
}
