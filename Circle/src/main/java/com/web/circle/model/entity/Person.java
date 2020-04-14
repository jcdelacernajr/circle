package com.web.circle.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Juanito C. Dela Dela Cerna Jr. March 2020
 */
@Entity
@Table(name = "person")
public class Person extends CircleAuditing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private long personId;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name = "id")
	private long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "middle_name")
	private String middleName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "name_extension")
	private String nameExtension;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "sex")
	private String sex;
	
	@Column(name = "status")
	private String starus;
	
	@Column(name = "citizenship")
	private String citizenship;
	
	@Column(name = "weight")
	private Float weight;
	
	@Column(name = "height")
	private Float height;
	
	@Column(name = "blood_type")
	private String blood_type;
	
	@Column(name = "tin_no")
	private String tinNo;
	
	@Column(name = "gsis_no")
	private String gsisNo;
	
	@Column(name = "pagibig_no")
	private String pagibigNo;
	
	@Column(name = "sss_no")
	private String sss_no;
	
	@Column(name = "philhealt_no")
	private String philhealtNo;
	
	@Column(name = "date_of_berth")
	private String dateOfBerth;
	
	@Column(name = "place_of_berth")
	private String placeOfBerth;

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNameExtension() {
		return nameExtension;
	}

	public void setNameExtension(String nameExtension) {
		this.nameExtension = nameExtension;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStarus() {
		return starus;
	}

	public void setStarus(String starus) {
		this.starus = starus;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public String getBlood_type() {
		return blood_type;
	}

	public void setBlood_type(String blood_type) {
		this.blood_type = blood_type;
	}

	public String getTinNo() {
		return tinNo;
	}

	public void setTinNo(String tinNo) {
		this.tinNo = tinNo;
	}

	public String getGsisNo() {
		return gsisNo;
	}

	public void setGsisNo(String gsisNo) {
		this.gsisNo = gsisNo;
	}

	public String getPagibigNo() {
		return pagibigNo;
	}

	public void setPagibigNo(String pagibigNo) {
		this.pagibigNo = pagibigNo;
	}

	public String getSss_no() {
		return sss_no;
	}

	public void setSss_no(String sss_no) {
		this.sss_no = sss_no;
	}

	public String getPhilhealtNo() {
		return philhealtNo;
	}

	public void setPhilhealtNo(String philhealtNo) {
		this.philhealtNo = philhealtNo;
	}

	public String getDateOfBerth() {
		return dateOfBerth;
	}

	public void setDateOfBerth(String dateOfBerth) {
		this.dateOfBerth = dateOfBerth;
	}

	public String getPlaceOfBerth() {
		return placeOfBerth;
	}

	public void setPlaceOfBerth(String placeOfBerth) {
		this.placeOfBerth = placeOfBerth;
	}
	
	
}
