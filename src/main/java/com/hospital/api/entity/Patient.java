package com.hospital.api.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="NAME",length = 225)
	@Size(min=3, message = "Name Should have atleast 3 charactor")
	private String name;
	
	@Column(name="ADDRESS",length = 1500)
	@Size(min=10, message = "Address Should have atleast 10 charactor")
	private String address;
	
	@Column(name="EMAIL",length = 225)
	@Email(message = "Email id is invalidate")
	private String email;
	
	@Column(name="PHONE_NO",length = 225)
	@Size(min=10, message = "Phone No Should have atleast 10 number")
	@Pattern(regexp = "^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$",message = "PhoneNo at least 10 number + country code")
	private String phoneNo;
	
	@Column(name="PASSWORD",length = 225)
	@Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$",message = "Password must contain one upper character, one lower character, and a number.Max length 15 and min length 8")
	private String password;
	
	
	@OneToMany(mappedBy = "patient")
	private List<Appointment> appointments;
	
	public Patient() {
		// TODO Auto-generated constructor stub
	}
	
}
