package com.backend.crud_spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Lesson {
     @Id
     @NotNull
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

     @NotBlank
     @Column(length = 100, nullable = false)
     private String name;

     @NotBlank
     @Column(length = 11, nullable = false)
     private String videoUrl;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "course_id", nullable = false)
     private Course course;
}