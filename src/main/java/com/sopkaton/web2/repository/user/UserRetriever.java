package com.sopkaton.web2.repository.user;

import com.sopkaton.web2.common.core.MemberNotFoundException;
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
