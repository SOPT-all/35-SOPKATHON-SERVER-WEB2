package com.sopkaton.web2.repository;

import com.sopkaton.web2.common.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRetriever {
    private final UserRepository userRepository;

    public User findUserById(long id) {
        return userRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }

}
