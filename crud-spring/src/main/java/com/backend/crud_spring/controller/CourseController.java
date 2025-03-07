package com.backend.crud_spring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

     @GetMapping("/{id}")
     public ResponseEntity<Course> findById(@PathVariable Long id) {

          return courseRepository.findById(id)
                    .map(record -> ResponseEntity.ok().body(record))
                    .orElse(ResponseEntity.notFound().build());

     }

     @PostMapping
     public ResponseEntity<Course> createCourse(@RequestBody Course course) {
          return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
     }

     @PutMapping("/{id}")
     public ResponseEntity<Course> update(@RequestBody Course course, @PathVariable Long id) {
          return courseRepository.findById(id)
                    .map(record -> {
                         record.setName(course.getName());
                         record.setCategory(course.getCategory());

                         Course updated = courseRepository.save(record);

                         return ResponseEntity.ok().body(updated);
                    })
                    .orElse(ResponseEntity.notFound().build());
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> delete(@PathVariable Long id) {
          return courseRepository.findById(id)
               .map(record -> {
                    courseRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
               })
               .orElse(ResponseEntity.notFound().build());
     }
}
