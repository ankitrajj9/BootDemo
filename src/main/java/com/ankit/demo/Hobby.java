package com.ankit.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="hobby")
public class Hobby {
	public Hobby() {}

	public Hobby(Long hobbyId, String hobbyName, Student student) {
		super();
		this.hobbyId = hobbyId;
		this.hobbyName = hobbyName;
		this.student = student;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="hobbyId")
	@JsonIgnore
	private Long hobbyId;
	
	private String hobbyName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="studentId")
	@JsonIgnore
	private Student student;

	public Long getHobbyId() {
		return hobbyId;
	}

	public void setHobbyId(Long hobbyId) {
		this.hobbyId = hobbyId;
	}

	public String getHobbyName() {
		return hobbyName;
	}

	public void setHobbyName(String hobbyName) {
		this.hobbyName = hobbyName;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	

}
