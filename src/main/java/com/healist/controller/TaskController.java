package com.healist.controller;

import com.healist.model.Quote;
import com.healist.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private QuoteRepository quoteRepository;

    // 모든 추천 할일 가져오기
    @GetMapping
    public List<Quote> getAllTasks() {
        return quoteRepository.findAll();
    }

    // 새로운 할일 추가하기
    @PostMapping
    public Quote addTask(@RequestBody Quote quote) {
        return quoteRepository.save(quote);
    }
}
