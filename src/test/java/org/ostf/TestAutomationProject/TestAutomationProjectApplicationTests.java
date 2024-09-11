package org.ostf.TestAutomationProject;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TestAutomationProjectApplicationTests {

	private final ObjectsService objectsService;
	private final ObjectMapper objectMapper;
	private final List<String> phoneNames;

	Logger logger = LogManager.getLogger();

	// Constructor to initialize objects with injected values
	public TestAutomationProjectApplicationTests(
			@Value("${external.api.url}") String apiUrl,
			@Value("${objects.api.endpoint}") String endpoint,
			@Value("#{'${phones}'.split(',')}") List<String> phoneNames) {
		// Initialize ObjectMapper manually
		this.objectMapper = new ObjectMapper();

		// Initialize BaseService and pass it to ObjectsService with apiUrl from properties
		BaseService baseService = new BaseService(apiUrl);

		// Pass the endpoint manually when creating ObjectsService
		this.objectsService = new ObjectsService(baseService, endpoint);

		// Set phoneNames from the property
		this.phoneNames = phoneNames;
	}

	@Test
	public void testGetAllApplePhones() throws IOException {
		// If we expand the use case to say "get all Samsung phones" we should make this a configurable item
		logger.info("Executing test to retrieve only Apple iPhones");
		List<Product> products = loadJsonData();
		List<String> applePhoneNames = products.stream()
				.map(Product::getName)
				.filter(name -> name.contains("Apple iPhone"))
				.toList();

		applePhoneNames.forEach(name -> logger.info("Apple iPhone: {}", name));
		org.junit.jupiter.api.Assertions.assertFalse(applePhoneNames.isEmpty(), "There should be at least one Apple phone.");
	}

	@Test
	public void testGetLowestPriceProduct() throws IOException {
		logger.info("Executing test to get lowest cost device");
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
		logger.info("Executing test to check all ID fields are Not Null");
		List<Product> products = loadJsonData();

		boolean allIdsNotNull = products.stream().allMatch(product -> product.getId() != null);
		logger.info("All IDs are non-null: {}", allIdsNotNull);
		org.junit.jupiter.api.Assertions.assertTrue(allIdsNotNull, "All ID fields should be non-null.");
	}


	@Test
	public void testGetLowestPricedPhone() throws IOException {
		logger.info("Executing test to get lowest price phone :`");
		List<Product> products = loadJsonData();

		Optional<Product> lowestPricedProduct = products.stream()
				.filter(product -> {
					String name = product.getName();
					return phoneNames.stream().anyMatch(name::contains);
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
