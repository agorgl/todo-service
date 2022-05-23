package com.example.todo.model;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Todo {
    @Id
    @GeneratedValue
    Long id;

    @NotNull
    @NotBlank
    String title;

    @Nullable
    String description;
}
