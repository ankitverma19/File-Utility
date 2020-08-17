package com.dbs.test.file;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DBSFileApplication {

	/**
	 * Spring bootstrap method.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DBSFileApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${application-description}") final String appDesciption,
								 @Value("${application-version}") final String appVersion) {
		return new OpenAPI()
				.info(new Info()
						.title("DBS File/Folder details application")
						.version(appVersion)
						.description(appDesciption)
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
