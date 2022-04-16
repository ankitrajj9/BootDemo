package com.ankit.demo;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="student")
public class Student {
	public Student() {}

	
	public Set<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(Set<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}


	public Student(Long studentId, String studentName,String emailId, String gender, String address,String country,String state, String city, Set<Hobby> hobbies,
			Set<Skill> skills) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.emailId = emailId;
		this.gender = gender;
		this.address = address;
		this.country=country;
		this.state=state;
		this.city = city;
		this.hobbies = hobbies;
		this.skills = skills;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="studentId")
	private Long studentId;
	
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	 

	private String studentName;
	
	private String emailId;
	
	private String password;
	
	private Date date;
	
	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	private String gender;
	
	private String address;
	
	private String country;
	
	private String state;
	
	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	private String city;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="student")
	private Set<Hobby> hobbies = new HashSet<Hobby>();
	
	
	  public Set<StudentImage> getStudentImage() {
		return studentImage;
	}


	public void setStudentImage(Set<StudentImage> studentImage) {
		this.studentImage = studentImage;
	}


	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="student")
	  private Set<Skill> skills = new HashSet<Skill>();
	  
	  @OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="student")
	  private Set<StudentImage> studentImage = new HashSet<StudentImage>();
	 
	  @JsonIgnore
	  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinTable(
	            name = "users_roles",
	            joinColumns = @JoinColumn(name = "studentId"),
	            inverseJoinColumns = @JoinColumn(name = "roleId")
	            )
	    private Set<Role> roles = new HashSet<>();

	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	} 
	
	
	

}
