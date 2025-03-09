package com.backend.crud_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.backend.crud_spring.model.Course;
import com.backend.crud_spring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class CourseService {
     private final CourseRepository courseRepository;

     public CourseService(CourseRepository courseRepository) {
          this.courseRepository = courseRepository;
     }

     public List<Course> list() {
          return courseRepository.findAll();
     }

     public Optional<Course> findById(@PathVariable @NotNull @Positive Long id) {

          return courseRepository.findById(id);

     }

     public Course createCourse(@Valid Course course) {
          return courseRepository.save(course);
     }

     public Optional<Course> update(Course course, Long id) {
          return courseRepository.findById(id)
               .map(record -> {
                    record.setName(course.getName());
                    record.setCategory(course.getCategory());

                    return courseRepository.save(record);
               });
     }

     public boolean delete(@PathVariable @NotNull @Positive Long id) {
          return courseRepository.findById(id)
                    .map(_ -> {
                         courseRepository.deleteById(id);
                         return true;
                    })
                    .orElse(false);
     }
}
