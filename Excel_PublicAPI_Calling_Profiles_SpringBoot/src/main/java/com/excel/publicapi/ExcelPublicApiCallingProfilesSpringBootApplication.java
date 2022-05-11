package com.excel.publicapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class ExcelPublicApiCallingProfilesSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelPublicApiCallingProfilesSpringBootApplication.class, args);
	}

}
