package com.example.todo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.todo.exception.ResourceNotFoundException;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

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
