package com.healist.service;

import com.healist.model.Todolist;
import com.healist.repository.TodolistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodolistService {

    @Autowired
    private TodolistRepository todolistRepository;

    public Todolist addWork(String work) {
        Todolist newTask = new Todolist();
        newTask.setWork(work);
        return todolistRepository.save(newTask); // 할 일 추가
    }
}
