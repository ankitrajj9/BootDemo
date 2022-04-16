package com.ankit.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="StudentImage")
public class StudentImage {
	
	public StudentImage() {}
	
	public StudentImage(Long imageId, String imageDescription, Student student, byte[] image, String path) {
		super();
		this.imageId = imageId;
		this.imageDescription = imageDescription;
		this.student = student;
		this.image = image;
		this.path = path;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="imageId")
	private Long imageId;
	
	

	private String imageDescription;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="studentId")
	private Student student;
	
	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getImageDescription() {
		return imageDescription;
	}

	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Lob
	private byte[] image;
	
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
