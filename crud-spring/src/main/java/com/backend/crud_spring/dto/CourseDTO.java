package com.backend.crud_spring.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CourseDTO(
     Long id, 
     @NotBlank @NotNull @Length(min = 5, max = 50) String name, 
     @NotNull @Length(max = 10) @Pattern(regexp = "Back-end|Front-end") String category,
     List<LessonDTO> lessons
     ) {
     
}
