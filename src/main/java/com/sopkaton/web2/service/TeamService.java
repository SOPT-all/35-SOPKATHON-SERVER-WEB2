package com.sopkaton.web2.service;


import com.sopkaton.web2.common.api.CustomException;
import com.sopkaton.web2.common.api.ErrorCode;
import com.sopkaton.web2.controller.request.TeamCreateRequest;
import com.sopkaton.web2.repository.team.Team;
import com.sopkaton.web2.repository.team.TeamRepository;
import com.sopkaton.web2.repository.user.User;
import com.sopkaton.web2.repository.user.UserRepository;
import com.sopkaton.web2.repository.userteam.UserTeam;
import com.sopkaton.web2.repository.userteam.UserTeamRepository;
import com.sopkaton.web2.service.response.TeamCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final UserTeamRepository userTeamRepository;
    private final UserRepository userRepository;


    @Transactional
    public TeamCreateResponse createTeam(long userId, TeamCreateRequest teamCreateRequest) {


        //팀 생성 후 저장
        Team team = new Team(teamCreateRequest.name(), teamCreateRequest.minimumStartNumber());
        Team savedTeam = teamRepository.save(team);
        TeamCreateResponse teamCreateResponse = new TeamCreateResponse(savedTeam.getId(), savedTeam.getName(), savedTeam.getCode());

        //유저 찾기
        User findUser = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        //userTeam 저장
        UserTeam userTeam = new UserTeam(findUser, savedTeam);
        UserTeam savedUserTeam = userTeamRepository.save(userTeam);

        //return teamCreateResponse
        return teamCreateResponse;

    }




}
