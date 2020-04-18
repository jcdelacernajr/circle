package com.web.circle.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


/**
 * Base controller
 * @since April 2020
 * */
public class BaseContoller {

	/**
	 * For file download
	 * 
	 * @param fileName
	 * @param baseURL
	 * */
	public String fileDownloadUrl(final String fileName, final String baseURL){
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(baseURL).path(fileName).toUriString();
    }
}
