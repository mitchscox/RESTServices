package org.ostf.TestAutomationProject;

import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = { ManagementWebSecurityAutoConfiguration.class })
public class TestAutomationProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestAutomationProjectApplication.class, args);
	}
}