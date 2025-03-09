package com.backend.crud_spring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
@SQLRestriction("status<> 'Inativo'")
@Entity
public class Course {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
     
     @NotBlank
     @NotNull
     @Column(length = 50, nullable = false)
     @Length(min = 5, max = 50)
     private String name;
     
     @NotNull
     @Pattern(regexp = "Back-end|Front-end")
     @Column(length = 10, nullable = false)
     @Length(max = 10)
     private String category;

     @NotNull
     @Pattern(regexp = "Ativo|Inativo")
     @Column(length = 10, nullable = false)
     @Length(max = 10)
     private String status = "Ativo";
}
