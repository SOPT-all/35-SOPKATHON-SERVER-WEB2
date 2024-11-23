package com.sopkaton.web2.repository.userteam;

import com.sopkaton.web2.common.api.CustomException;
import com.sopkaton.web2.common.api.ErrorCode;
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
        return userTeamRepository.findUserTeamByUserAndTeam(user, team).orElseThrow(() -> new CustomException(
                ErrorCode.USER_TEAM_NOT_FOUND));
    }
    public UserTeam findUserTeamByUserIdAndTeamId(Long userid, Long teamId) {
        return userTeamRepository.findUserTeamByUserIdAndTeamId(userid, teamId).orElseThrow(() -> new CustomException(
                ErrorCode.USER_TEAM_NOT_FOUND));
    }
}
