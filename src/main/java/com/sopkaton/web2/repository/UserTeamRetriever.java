package com.sopkaton.web2.repository;

import com.sopkaton.web2.common.UserTeamNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserTeamRetriever {
    private final UserTeamRepository userTeamRepository;

    public UserTeam findUserTeamByUserAndTeam (User user, Team team) {
        return userTeamRepository.findUserTeamByUserAndTeam(user, team).orElseThrow(UserTeamNotFoundException::new);
    }
}
