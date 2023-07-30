package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Course;
import com.example.demo.entity.Lessons;
import com.example.demo.service.TrainerService;

@Controller
@RequestMapping("/trainer")
public class TrainerController {

	@Autowired
	TrainerService trainerService;
	
	public TrainerController(TrainerService trainerService) {
		// TODO Auto-generated constructor stub
		this.trainerService = trainerService;
	}
	
	@PostMapping("/addCourse")
	public String addCourse(/*@RequestParam("courseId") int courseId,*/ @RequestParam("courseName") String courseName, 
			@RequestParam("coursePrice") int coursePrice) {
		Course course = new Course();
		/*course.setCourse_id(courseId);*/
		course.setCourse_name(courseName);
		course.setCourse_price(coursePrice);
		
		Course c = trainerService.addCourse(course);
		if(c != null) {
			return "TrainerHome";
		}
		else {
			return "createCourseFail";
		}
	}
	
	@PostMapping("/lesson")
	public String lesson(@RequestParam("courseId")int courseId, @RequestParam("lessonId")int lessonId, @RequestParam("lessonName")String lessonName,@RequestParam("topics")String topics, @RequestParam("link")String link) {
		Course course = trainerService.getCourse(courseId);
		
		Lessons lessons = new Lessons(lessonName, topics, link, course);
		trainerService.addLesson(lessons);
		
		course.getLessons().add(lessons);
		
		trainerService.saveCourse(course);
		
		return "/trainerHome";
	}
	
	@GetMapping("/showCourses")
	public String showCourses(Model model) {
		List<Course> coursseList = trainerService.courseList();
		model.addAttribute("courseList", coursseList);
		return "courses";
	}
	
}
