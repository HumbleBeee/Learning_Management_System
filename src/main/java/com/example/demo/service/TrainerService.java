package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Course;
import com.example.demo.entity.Lessons;

public interface TrainerService {

	public Course addCourse(Course course);
	public String saveCourse(Course course);
	public String addLesson(Lessons lesson);
	public Course getCourse(int course_id);
	public List<Course> courseList();
}
