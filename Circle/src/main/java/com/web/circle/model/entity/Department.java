package com.web.circle.model.entity;

import javax.persistence.*;

/**
 * Department table
 * 
 * @author Juanito C. Dela Dela Cerna Jr. April 2020
 */
@Entity
@Table(name = "department")
public class Department extends CircleAuditing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_id")
	private long departmentId;
	
	@Column(name = "department_name")
	private String departmentName;

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
}
