package com.healist.controller;

import com.healist.model.Quote;
import com.healist.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private QuoteRepository quoteRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // login.html을 반환합니다.
    }

    @GetMapping("/main")
    public String showMainPage(Model model) {
        // 랜덤한 명언을 데이터베이스에서 가져옵니다.
        Optional<Quote> optionalQuote = quoteRepository.findRandomQuote();
        if (optionalQuote.isPresent()) {
            Quote quote = optionalQuote.get();
            model.addAttribute("quote", quote);
        } else {
            // 데이터가 없을 경우 기본 메시지를 설정합니다.
            model.addAttribute("quote", new Quote());
        }
        return "main"; // main.html 페이지를 반환합니다.
    }
}
