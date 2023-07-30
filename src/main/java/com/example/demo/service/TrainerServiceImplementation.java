package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Lessons;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.LessonRepository;

@Service
public class TrainerServiceImplementation implements TrainerService {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	LessonRepository lessonRepository;
	
	@Override
	public Course addCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public String saveCourse(Course course) {
		// TODO Auto-generated method stub
		courseRepository.save(course);
		return "Course saved succesfully";
	}

	@Override
	public String addLesson(Lessons lesson) {
		// TODO Auto-generated method stub
		lessonRepository.save(lesson);
		return "Lesson added successfully";
	}

	@Override
	public Course getCourse(int course_id) {
		// TODO Auto-generated method stub
		return courseRepository.findById(course_id).get();
	}

	@Override
	public List<Course> courseList() {
		// TODO Auto-generated method stub
		return courseRepository.findAll();
	}

}
