package com.backend.crud_spring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.crud_spring.model.Course;
import com.backend.crud_spring.repository.CourseRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

     private CourseRepository courseRepository;
     
     @GetMapping
     public List<Course> list() {
          return courseRepository.findAll();
     }
}
