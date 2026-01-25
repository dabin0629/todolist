package com.healist.controller;

import com.healist.model.Saying;
import com.healist.repository.SayingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sayings")  // 경로 유지
public class SayingController {

    @Autowired
    private SayingRepository sayingRepository;

    // 모든 명언을 가져오는 엔드포인트
    @GetMapping("/all")  // 경로 수정: /api/sayings/all
    public List<Saying> getAllSayings() {
        return sayingRepository.findAll();
    }
}
