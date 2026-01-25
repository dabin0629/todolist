package com.healist.controller;

import com.healist.model.Quote;
import com.healist.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private QuoteService quoteService;

    // 메인 페이지를 위한 매핑을 설정합니다.
    @GetMapping("/mainpage") // URL 매핑 수정 -> "/mainPage"에서 "/main"으로 변경
    public String showMainPage(Model model) {
        Quote randomQuote = quoteService.getRandomQuote();
        model.addAttribute("quote", randomQuote);
        return "main"; // resources/templates/main.html을 반환
    }
}
