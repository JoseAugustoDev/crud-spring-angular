package com.backend.crud_spring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.backend.crud_spring.model.Course;
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
     public List<Course> list() {
          return courseService.list();
     }

     @GetMapping("/{id}")
     public ResponseEntity<Course> findById(@PathVariable @NotNull @Positive Long id) {

          return courseService.findById(id)
                    .map(record -> ResponseEntity.ok().body(record))
                    .orElse(ResponseEntity.notFound().build());

     }

     @PostMapping
     @ResponseStatus(code = HttpStatus.CREATED)
     public Course createCourse(@RequestBody @Valid Course course) {
          return courseService.createCourse(course);
     }

     @PutMapping("/{id}")
     public ResponseEntity<Course> update(@RequestBody Course course, @PathVariable Long id) {
          return courseService.update(course, id)
                    .map(record -> ResponseEntity.ok().body(record))
                    .orElse(ResponseEntity.notFound().build());
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
          if (courseService.delete(id)) {
               return ResponseEntity.noContent().<Void>build();
          }

          return ResponseEntity.notFound().build();
     }
}
