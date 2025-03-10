package com.backend.crud_spring.dto.mapper;

import org.springframework.stereotype.Component;

import com.backend.crud_spring.dto.CourseDTO;
import com.backend.crud_spring.enums.Category;
import com.backend.crud_spring.enums.Status;
import com.backend.crud_spring.model.Course;

@Component
public class CourseMapper {
     public CourseDTO toDTO(Course course) {
          return new CourseDTO(
               course.getId(), course.getName(), course.getCategory().getValue(), course.getLessons()
          );
     }

     public Course toEntity(CourseDTO courseDTO) {
          if (courseDTO == null) {
               return null;
          }

          Course course = new Course();
          
          if (courseDTO.id() != null) {
               course.setId(courseDTO.id());
          }

          course.setName(courseDTO.name());
          course.setCategory(converCategoryValue(courseDTO.category()));
          course.setStatus(Status.ATIVO);

          return course;
     }

     public Category converCategoryValue(String value) {
          if (value == null) {
               return null;
          }

          return switch (value) {
               case "Front-end" -> Category.FRONTEND;
               case "Back-end" -> Category.BACKEND;
               default -> throw new IllegalArgumentException("Categoria Inválida");
          };
     }
}
