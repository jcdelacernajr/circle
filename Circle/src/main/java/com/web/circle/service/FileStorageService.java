package com.web.circle.service;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.web.circle.exception.FileStorageException;
import com.web.circle.model.FileMetaDataModel;
import com.web.circle.model.entity.Users;

/**
 * 
 * 
 * @author jr April 2020
 * */
public interface FileStorageService {

	FileMetaDataModel store(MultipartFile file, Users user) throws FileStorageException;
	List<Path> getAllFiles();
	FileMetaDataModel getFile(String fileName) throws FileNotFoundException;
}
