package com.healist.controller;

import com.healist.service.UserService;
import com.healist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public void login(@RequestParam String userid, @RequestParam String userpw, HttpServletResponse response, HttpSession session) throws IOException {
        boolean isValidUser = userService.validateUser(userid, userpw);
        if (isValidUser) {
            // 유저 정보 가져오기
            User user = userService.getUserById(userid);
            session.setAttribute("userName", user.getName()); // 현재 사용자의 이름을 세션에 저장
            response.sendRedirect("/mainpage"); // 로그인 성공 시 메인 페이지로 리다이렉트
        } else {
            response.sendRedirect("/login?error=true"); // 로그인 실패 시 로그인 페이지로 리다이렉트
        }
    }
}
