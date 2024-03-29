package com.web.circle.model;

import org.springframework.core.io.Resource;

import lombok.Getter;
import lombok.Setter;

/**
 * File meta data
 * 
 * @author jr April 2020
 * */
@Setter
@Getter
public class FileMetaDataModel {

	private Long fileId;
	private String fileName;
	private String url;
	private String mime;
	private long size;
	private Resource resource;
	
	public FileMetaDataModel() {
		
	}

	public FileMetaDataModel(Long fileId, String fileName, String url, String mime, long size, Resource resource) {
		this.fileId = fileId;
		this.fileName = fileName;
		this.url = url;
		this.mime = mime;
		this.size = size;
		this.resource = resource;
	}
	
}
