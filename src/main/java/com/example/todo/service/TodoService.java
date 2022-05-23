package com.example.todo.service;

import java.util.List;
import java.util.Optional;

import com.example.todo.exception.ResourceNotFoundException;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Transactional(readOnly = true)
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Todo> findTodoById(long id) {
        return todoRepository.findById(id);
    }

    @Transactional
    public void deleteTodoById(long id) {
        findTodoById(id)
            .ifPresentOrElse(todo -> todoRepository.delete(todo),
                             () -> { throw new ResourceNotFoundException(); });
    }
}
