package com.backend.crud_spring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backend.crud_spring.dto.CourseDTO;
import com.backend.crud_spring.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

     private final CourseService courseService;

     public CourseController(CourseService courseService) {
          this.courseService = courseService;
     }

     @GetMapping
     public List<CourseDTO> list() {
          return courseService.list();
     }

     @GetMapping("/{id}")
     public CourseDTO findById(@PathVariable @NotNull @Positive Long id) {

          return courseService.findById(id);

     }

     @PostMapping
     @ResponseStatus(code = HttpStatus.CREATED)
     public CourseDTO createCourse(@RequestBody @Valid CourseDTO course) {
          return courseService.createCourse(course);
     }

     @PutMapping("/{id}")
     public CourseDTO update(@RequestBody @Valid @NotNull CourseDTO course, @PathVariable @NotNull @Positive Long id) {
          return courseService.update(id, course);
     }

     @DeleteMapping("/{id}")
     @ResponseStatus(code = HttpStatus.NO_CONTENT)
     public void delete(@PathVariable @NotNull @Positive Long id) {
          courseService.delete(id);
     }
}
