package com.example.todo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.example.todo.dto.TodoDto;
import com.example.todo.exception.ResourceNotFoundException;
import com.example.todo.mapper.TodoMapper;
import com.example.todo.service.TodoService;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@SecurityRequirement(name = "security_auth")
public class TodoController {
    private final TodoMapper todoMapper;
    private final TodoService todoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoDto create(@RequestBody @NotNull @Valid TodoDto todoDto) {
        return Optional.of(todoDto)
            .map(todoMapper::toEntity)
            .map(todoService::createTodo)
            .map(todoMapper::toDto)
            .orElseThrow();
    }

    @GetMapping
    @PageableAsQueryParam
    public List<TodoDto> list(Pageable pageable) {
        return todoService.getAllTodos(pageable)
            .stream()
            .map(todoMapper::toDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TodoDto get(@PathVariable("id") @NotNull Long id) {
        return todoService
            .findTodoById(id)
            .map(todoMapper::toDto)
            .orElseThrow(ResourceNotFoundException::new);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") @NotNull Long id) {
        todoService.deleteTodoById(id);
    }
}
