package com.example.todo.dto;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TodoDto {
    Long id;
    @NotNull
    @NotBlank
    String title;
    @Nullable
    String description;
}
