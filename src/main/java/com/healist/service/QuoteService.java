package com.healist.service;

import com.healist.model.Quote;
import com.healist.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    // 모든 명언을 가져오는 메서드 추가
    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    // 이미 존재하는 메서드 (예시로 추가)
    public Quote getRandomQuote() {
        return quoteRepository.findRandomQuote().orElse(null);
    }
}
