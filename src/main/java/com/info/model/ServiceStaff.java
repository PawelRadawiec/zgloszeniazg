package com.info.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "service_staff")
public class ServiceStaff {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "first_name")
	@NotEmpty(message = "Pole nie może być puste")
	private String firstName;

	@Column(name = "last_name")
	@NotEmpty(message = "Pole nie może być puste")
	private String lastName;

	@Column(name = "type_service")
	@NotEmpty(message = "Pole nie może być puste")
	private String typeService;

	@Column(name = "diet")
	@NotEmpty(message = "Pole nie może być puste")
	private String diet;

	@Column(name = "email", unique = true)
	@Email(message = "Wprowadź poprawny email")
	@NotEmpty(message = "Pole nie może być puste")
	private String email;

	@Column(name = "troops")
	@NotEmpty(message = "Pole nie może być puste")
	private String troops;

	@Column(name = "ensign")
	@NotEmpty(message = "Pole nie może być puste")
	private String ensign;

	@Column(name = "password")
	@Length(min = 5, message = "haslo za krotkie")
	@NotEmpty(message = "Pole nie może być puste")
	private String password;

	@Column(name = "register_data")
	private String data;

	@Column(name = "role")
	private String role;

	public ServiceStaff() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTypeService() {
		return typeService;
	}

	public void setTypeService(String typeService) {
		this.typeService = typeService;
	}

	public String getDiet() {
		return diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTroops() {
		return troops;
	}

	public void setTroops(String troops) {
		this.troops = troops;
	}

	public String getEnsign() {
		return ensign;
	}

	public void setEnsign(String ensign) {
		this.ensign = ensign;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
