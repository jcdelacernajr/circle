package com.web.circle.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Upload file table
 * 
 * @author Juanito C. Dela Dela Cerna Jr. April 2020
 */
@Entity
@Table(name = "upload_file")
public class UploadFile extends CircleAuditing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "upload_file_id")
	private long uploadFileId;
	
	@Column(name = "uploaded_by_user_fk")
	private Users user;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "display_name")
	private String displayName;
	
	@Column(name = "extension")
	private String extension;
	
	@Column(name = "size")
	private Float size;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "is_active")
	private Boolean isActive;

	public long getUploadFileId() {
		return uploadFileId;
	}

	public void setUploadFileId(long uploadFileId) {
		this.uploadFileId = uploadFileId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Float getSize() {
		return size;
	}

	public void setSize(Float size) {
		this.size = size;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
}
