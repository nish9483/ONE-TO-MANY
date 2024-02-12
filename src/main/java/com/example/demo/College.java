package com.example.demo;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class College {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;
	private String collegeName;
	private String street;
	
	@OneToMany(mappedBy="college",cascade=CascadeType.ALL)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({ "id", "collegeName","street", "college" })
	private Set<Lecture>lecture;
	
	College()
	{
		
	}

	public College(int id, String collegeName, String street) {
		super();
		this.id = id;
		this.collegeName = collegeName;
		this.street = street;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Set<Lecture> getLecture() {
		return lecture;
	}

	public void setLecture(Set<Lecture> lecture) {
		this.lecture = lecture;
	}

	@Override
	public String toString() {
		return "College [id=" + id + ", collegeName=" + collegeName + ", street=" + street + ", lecture=" + lecture
				+ "]";
	}
	
	
}
