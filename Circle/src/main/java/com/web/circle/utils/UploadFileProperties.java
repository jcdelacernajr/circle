package com.web.circle.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Upload file configuration properties
 * 
 * @author jr April 2020
 * */
@Configuration
@ConfigurationProperties(prefix = "file")
@EnableConfigurationProperties
public class UploadFileProperties {

	private String uploadDir;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	
}
