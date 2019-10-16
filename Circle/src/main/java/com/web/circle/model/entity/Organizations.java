package com.web.circle.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author jr
 * */
@Entity
@Table(name = "organizations")
public class Organizations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "organization_id")
	private long organizationId;
	
	@Column(name = "establishment_name")
	private String establishmentName;
}
