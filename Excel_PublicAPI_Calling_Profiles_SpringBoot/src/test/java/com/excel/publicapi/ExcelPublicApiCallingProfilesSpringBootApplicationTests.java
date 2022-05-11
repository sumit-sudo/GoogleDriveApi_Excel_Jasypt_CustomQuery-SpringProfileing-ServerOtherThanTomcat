package com.excel.publicapi;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExcelPublicApiCallingProfilesSpringBootApplicationTests {

	/*
	@Test
	void contextLoads() {
		 PooledPBEStringEncryptor encryptor=new PooledPBEStringEncryptor();
		 SimpleStringPBEConfig config=new SimpleStringPBEConfig();
		 config.setPassword("secret_key");
		 config.setAlgorithm("PBEWithMD5AndDES");
	     config.setKeyObtentionIterations("1000");
	     config.setPoolSize("1");
	     config.setProviderName("SunJCE");
	     config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
	     config.setStringOutputType("base64");
	     encryptor.setConfig(config);
	     String plainText="item";
	     String encryptedUserName=encryptor.encrypt(plainText);
	     String plainPass="sumit06420";
	     String encryptedUserpass=encryptor.encrypt(plainText);
	     
	     System.out.println("encryptedUserName "+encryptedUserName+"encryptedUserpass "+encryptedUserpass);
	     
	}*/

}
