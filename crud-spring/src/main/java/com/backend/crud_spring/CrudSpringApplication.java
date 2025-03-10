package com.backend.crud_spring;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.backend.crud_spring.enums.Category;
import com.backend.crud_spring.model.Course;
import com.backend.crud_spring.model.Lesson;
import com.backend.crud_spring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return _ -> {
			courseRepository.deleteAll();

			Course curso = new Course();
			curso.setName("Angular + Spring");
			curso.setCategory(Category.FRONTEND);

			Lesson lesson = new Lesson();
			lesson.setName("OneToMany");
			lesson.setVideoUrl("Nb4uxLxdvxo");
			lesson.setCourse(curso);
			
			curso.getLessons().add(lesson);

			courseRepository.save(curso);
		};
	}
}
