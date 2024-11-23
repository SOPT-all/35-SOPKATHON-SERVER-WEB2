package com.sopkaton.web2.service.team;

import com.sopkaton.web2.common.api.CustomException;
import com.sopkaton.web2.common.api.ErrorCode;
import com.sopkaton.web2.common.api.SuccessCode;
import com.sopkaton.web2.controller.request.TeamCreateRequest;
import com.sopkaton.web2.repository.team.Team;
import com.sopkaton.web2.repository.team.TeamRepository;
import com.sopkaton.web2.repository.team.TeamRetriever;
import com.sopkaton.web2.repository.user.User;
import com.sopkaton.web2.repository.user.UserRepository;
import com.sopkaton.web2.repository.user.UserRetriever;
import com.sopkaton.web2.repository.userteam.UserTeam;
import com.sopkaton.web2.repository.userteam.UserTeamRepository;
import com.sopkaton.web2.service.response.TeamCreateResponse;
import com.sopkaton.web2.service.response.TeamResponse;
import com.sopkaton.web2.service.response.TeamsResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRetriever retriever;
    private final UserRetriever userRetriever;
    private final UserTeamRepository userTeamRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public TeamsResponse findTeamsByName(String name) {
        List<TeamResponse> list = retriever.findTeamsByName(name).stream().map(TeamResponse::of)
                .collect(Collectors.toList());

        return TeamsResponse.of(list);
    }

    @Transactional
    public TeamResponse registerTeam(long teamId, long userId, String code) {
        //비밀번호 맞으면 팀에 등록 다르면 예외를 던진다.
        Team team = retriever.findTeamById(teamId);
        if (!team.getCode().equals(code)) {
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCH);
        }
        User userById = userRetriever.findUserById(userId);
        if (userTeamRepository.existsByUserAndTeam(userById, team)) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_TEAM);
        }
        team.addCurrentNumber();
        UserTeam userteam = UserTeam.builder()
                .team(team)
                .user(userById)
                .chance(true)
                .build();


        userTeamRepository.save(userteam);
        return TeamResponse.of(team);
    }
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

        savedTeam.setCurrentNumber(1);


        //return teamCreateResponse
        return teamCreateResponse;

    }
}
