package com.blackadi.app.ws.mobileappws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.blackadi.app.ws.mobileappws", "com.blackadi.app.ws" })
public class MobileAppWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileAppWsApplication.class, args);
	}

}
