package com.backend.crud_spring.model;


import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import com.backend.crud_spring.enums.Category;
import com.backend.crud_spring.enums.Status;
import com.backend.crud_spring.enums.converters.CategoryConverter;
import com.backend.crud_spring.enums.converters.StatusConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
     
     //@Length(max = 10)
     //@Pattern(regexp = "Back-end|Front-end")
     @NotNull
     @Column(length = 10, nullable = false)
     @Convert(converter = CategoryConverter.class)
     private Category category;

     //@Length(max = 10)
     //@Pattern(regexp = "Ativo|Inativo")
     @NotNull
     @Column(length = 10, nullable = false)
     @Convert(converter = StatusConverter.class)
     private Status status = Status.ATIVO;
}
