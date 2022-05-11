package com.excel.publicapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;



public class GoogleDriveConnection {
	
	private static HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	
	private static final String APPLICATION_NAME="test";
	private static  String folderName="";
	private static  String folderId="";
	String newAccessToken="";
	
	private String secret_key="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";
	private String client_key="301167454063-5rnraqt7r2gifgj6mcr3u8m8u0sisadj.apps.googleusercontent.com";
	private String refreshToken="1//0462FUs-1L76ACgYIARAAGAQSNwF-L9IrhGLpo0hDlcWA4FPTxkTqdCoXvdsatdP0krvCQitb7oWH9LriqnknZjdoe7yvseHrt3E";
	
	@Autowired
	DefaultLazyEncryptor ide;
	
	@Scheduled(fixedRate = 300000,initialDelay = 0)
	public void generateAccessToken()
	{
		try {
			GoogleCredential refreshTokenCredential=new GoogleCredential().toBuilder().setJsonFactory(JSON_FACTORY)
					.setTransport(HTTP_TRANSPORT)
					.setClientSecrets(ide.decrypt(client_key), ide.decrypt(secret_key)).build();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
