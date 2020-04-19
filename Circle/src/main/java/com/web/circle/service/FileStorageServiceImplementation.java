package com.web.circle.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.apache.tika.detect.Detector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.web.circle.exception.FileStorageException;
import com.web.circle.model.FileMetaDataModel;
import com.web.circle.model.entity.Person;
import com.web.circle.model.entity.UploadFile;
import com.web.circle.model.entity.Users;
import com.web.circle.repository.PersonRepository;
import com.web.circle.repository.UploadFileRepository;
import com.web.circle.utils.UploadFileProperties;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author jr April 2020
 * */
@Slf4j
@Service("fileStorageService")
public class FileStorageServiceImplementation implements FileStorageService {
	
	@Autowired
	UploadFileProperties uploadFileProperties;
	@Autowired
	private UploadFileRepository uploadFileRepository;
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public FileMetaDataModel store(MultipartFile file, Users user) throws FileStorageException {
		// Timestamp
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// File name
		String fileName = StringUtils.cleanPath(timestamp.getTime() + "_" + file.getOriginalFilename());
		try {
			// File upload directory.
			Path uploadDir = getUploadDirectoryLocation().resolve(fileName);
			// Copy the file.
			Files.copy(file.getInputStream(), uploadDir, StandardCopyOption.REPLACE_EXISTING);
			
			// Record the uploaded file.
			UploadFile uploadFile = recordUploadedFile(file, user, uploadDir, fileName);
			// Record the person photo
			updatePhoto(user, uploadFile);
			
			// Set the file meta data.
			FileMetaDataModel mdata = new FileMetaDataModel();
			mdata.setFileName(fileName);
			mdata.setSize(file.getSize());
			mdata.setMime(file.getContentType());
			
			return mdata;
		} catch(IOException err) {
			log.error("Unable to copy file to the target location {}", err);
			throw new FileStorageException("Unable to store file "+ fileName);
		} 
	}
	
	/**
	 * Record the uploaded file
	 * 
	 * @param file
	 * @param user
	 * @param path
	 * @param fileName
	 * */
	private UploadFile recordUploadedFile(MultipartFile file, Users user, Path path, String fileName) {
		UploadFile uploadFile = new UploadFile(); 
		uploadFile.setDisplayName(file.getOriginalFilename());
		uploadFile.setExtension(file.getContentType());
		uploadFile.setFileName(fileName);
		uploadFile.setIsActive(true);
		uploadFile.setSize(file.getSize());
		uploadFile.setLocation(path.toString());
		
		// User data. // TODO...
		Users us = new Users();
		long userId = user.getUserId(); 
		us.setUserId(userId);
		uploadFile.setUser(us);
		// Execute the save function.
		return uploadFileRepository.save(uploadFile);
	}
	
	/**
	 * Update person table for photo profile.
	 * 
	 * @param user
	 * */
	private void updatePhoto(Users user, UploadFile uploadFile) {
		// Person id.
		long personId = user.getPerson().getPersonId();
		long uploadFileId = uploadFile.getUploadFileId();
		// Get the person data that need to update.
		Person person = personRepository.findById(personId).get();
		// Create an instance for uploaded file
		UploadFile file = new UploadFile();
		file.setUploadFileId(uploadFileId);
		// Set the data
		person.setUploadFile(file);
		// Record the person data
		personRepository.save(person);
		log.info("Function updatePhoto() ", person);
	}
	
	/**
	 * Based on the application.properties config file.
	 * @return path
	 * */
	private Path getUploadDirectoryLocation() {
		return Paths.get(uploadFileProperties.getUploadDir()).toAbsolutePath().normalize();
	}

	@Override
	public List<Path> getAllFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * Method to return the file as @Resource for the download.It read the file from the file system
     * and return it as @Resource
     * @param fileName
     * @return FileMetaDataModel
     * @throws FileNotFoundException
     */
	@Override
	public FileMetaDataModel getFile(String fileName) throws FileNotFoundException {
		Path path = getUploadDirectoryLocation().resolve(fileName).normalize();
		try {
			Resource resource = new UrlResource(path.toUri());
			if(resource.exists()) {
				Metadata mdata = getFileMetaDataInfo(resource);
				FileMetaDataModel data = new FileMetaDataModel();
				data.setResource(resource);
				data.setFileName(fileName);
				data.setSize(mdata.size());
				data.setMime(mdata.get(Metadata.CONTENT_TYPE));
				
				return data;
			} 
			else {
				throw new FileNotFoundException("File not found!");
			} 
		} catch (MalformedURLException err) {
			throw new FileNotFoundException("File not found!");
		}
	}
	
	/**
     * Helper method to get the file meta-data using Apache Tikka corre library.JDK also provide
     * way to read meta-data information but it's very limited and have lot of issues.
     * @param resource
     * @return Metadata
     */
	private Metadata getFileMetaDataInfo(Resource resource) {
		AutoDetectParser parser = new AutoDetectParser();
        Detector detector = parser.getDetector();
        Metadata metadata = new Metadata();
        try {
            metadata.set(Metadata.RESOURCE_NAME_KEY, resource.getFile().getName());
            TikaInputStream stream = TikaInputStream.get(resource.getInputStream());
            MediaType mediaType = detector.detect(stream,metadata);
            metadata.set(Metadata.CONTENT_TYPE, mediaType.toString());
        } catch (IOException e) {
            e.printStackTrace();
            //fallback to default content type
            metadata.set(Metadata.CONTENT_TYPE, "application/octet-stream");

        }
        return metadata;
	}
}
