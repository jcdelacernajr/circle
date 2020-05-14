package com.web.circle.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * System object
 * 
 * @autor Juanito C. Dela Cerna Jr. May 2020
 * */
@Entity
@Table(name = "system")
public class System extends CircleAuditing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "system_id")
	private long systemId;
	
	@Column(name = "system_label")
	private String label;
	
	@Column(name = "active")
	private Boolean active;

	public long getSystemId() {
		return systemId;
	}

	public void setSystemId(long systemId) {
		this.systemId = systemId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
}
