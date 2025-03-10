package com.backend.crud_spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
     @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
     private Course course;
}