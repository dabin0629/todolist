package com.healist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListController {

    @GetMapping("/list")
    public String showListPage() {
        return "list"; // "list.html" 파일을 보여줍니다.
    }
}
