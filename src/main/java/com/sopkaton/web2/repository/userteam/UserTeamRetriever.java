package com.sopkaton.web2.repository.userteam;

import com.sopkaton.web2.common.core.UserTeamNotFoundException;
import com.sopkaton.web2.repository.team.Team;
import com.sopkaton.web2.repository.user.User;
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
