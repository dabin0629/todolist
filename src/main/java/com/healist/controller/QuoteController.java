package com.healist.controller;

import com.healist.model.Quote;
import com.healist.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    // 모든 명언을 가져오는 API 엔드포인트
    @GetMapping("/tasks")
    public List<Quote> getAllTasks() {
        return quoteService.getAllQuotes();
    }
}
