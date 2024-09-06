package org.ostf.TestAutomationProject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestAutomationProjectApplicationTests {

	@Value("${external.api.url}")
	String url;

	@Autowired
	private ObjectsService objectsService;

	Logger logger = LogManager.getLogger();

	@Test
	void testPackInit() {
		logger.info("Beginning Test Pack Run");
		logger.info("URL = " + url);
	}


	@Test
	void executeObjectsServiceTests() {
		ObjectsServiceTest objectsServiceTest = new ObjectsServiceTest(objectsService);
		objectsServiceTest.testGetAllObjects();
		objectsServiceTest.testGetAllApplePhones();
		objectsServiceTest.testGetPhoneWithLowestPrice();
		objectsServiceTest.testAllIdsAreNotNull();
	}
}