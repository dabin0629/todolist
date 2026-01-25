package com.healist.service;

import com.healist.model.User;
import com.healist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 기존 유효성 검사 메서드
    public boolean validateUser(String userid, String userpw) {
        // findById 메서드를 호출하여 유저가 있는지 확인합니다.
        Optional<User> user = userRepository.findById(userid);

        // 유저가 존재하고 비밀번호가 일치하는지 검사
        return user.isPresent() && user.get().getPw().equals(userpw);
    }

    // 새로운 메서드 추가: 사용자 ID로 유저 정보를 가져오는 메서드
    public User getUserById(String userid) {
        return userRepository.findById(userid).orElse(null);
    }
}
