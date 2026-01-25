package com.healist.controller;

import com.healist.model.Saying;
import com.healist.repository.SayingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/hall")
public class HallController {

    @Autowired
    private SayingRepository sayingRepository;

    // 명언의 전당 페이지로 이동하고 모든 명언을 전달합니다.
    @GetMapping
    public String showHallPage(Model model) {
        List<Saying> sayings = sayingRepository.findAll();
        model.addAttribute("sayings", sayings);
        return "hall"; // hall.html 템플릿
    }

    // 모든 명언을 API로 가져오는 엔드포인트
    @GetMapping("/api/sayings")
    @ResponseBody
    public List<Saying> getAllSayings() {
        return sayingRepository.findAll();
    }

    // 새로운 명언 추가
    @PostMapping("/api/sayings/add")
    @ResponseBody
    public ResponseEntity<Saying> addSaying(@RequestBody Saying newSaying, HttpSession session) {
        // 세션에서 사용자 이름을 가져옵니다.
        String userName = (String) session.getAttribute("userName");

        if (userName == null || userName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null); // 사용자 정보가 없으면 잘못된 요청
        }

        if (newSaying.getQuote() == null || newSaying.getQuote().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);  // 명언 내용이 없으면 잘못된 요청
        }

        // 사용자 이름을 설정
        newSaying.setAuthor(userName);
        Saying savedSaying = sayingRepository.save(newSaying);
        return ResponseEntity.ok(savedSaying);
    }
}
