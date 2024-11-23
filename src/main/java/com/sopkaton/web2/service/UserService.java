package com.sopkaton.web2.service;

import com.sopkaton.web2.controller.request.UserPostRequest;
import com.sopkaton.web2.repository.user.User;
import com.sopkaton.web2.repository.user.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long createUser(UserPostRequest userPostRequest) {
        //전화번호 검증
        //비밀번호 검증

        //유저 저장
        User userItem = new User(userPostRequest.phoneNumber(), userPostRequest.password());
        userRepository.save(userItem);

        Long userId = userItem.getId();
        return userId;
    }


}
