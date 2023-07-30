package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	int course_id;
	
	@Column(name = "course_name")
	String course_name;
	
	@Column(name = "course_price")
	int course_price;
	
	@OneToMany
	List<Lessons> lessons;

	public Course() {
		// TODO Auto-generated constructor stub
	}
	
	public Course(String course_name, int course_price, List<Lessons> lessons) {
		super();
		this.course_name = course_name;
		this.course_price = course_price;
		this.lessons = lessons;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public int getCourse_price() {
		return course_price;
	}

	public void setCourse_price(int course_price) {
		this.course_price = course_price;
	}

	public List<Lessons> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lessons> lessons) {
		this.lessons = lessons;
	}

	@Override
	public String toString() {
		return "Course [course_id=" + course_id + ", course_name=" + course_name + ", course_price=" + course_price
				+ ", lessons=" + lessons + "]";
	}
	
}
