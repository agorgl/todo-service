package com.example.todo.mapper;

import com.example.todo.dto.TodoDto;
import com.example.todo.model.Todo;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todo toEntity(TodoDto todoDto);
    TodoDto toDto(Todo todo);
}
