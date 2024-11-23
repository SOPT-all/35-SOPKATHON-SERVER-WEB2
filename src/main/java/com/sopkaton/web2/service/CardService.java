package com.sopkaton.web2.service;

import com.sopkaton.web2.common.core.CoreException;
import com.sopkaton.web2.common.api.CustomException;
import com.sopkaton.web2.common.api.ErrorCode;
import com.sopkaton.web2.repository.card.Card;
import com.sopkaton.web2.repository.card.CardRepository;
import com.sopkaton.web2.repository.mission.Mission;
import com.sopkaton.web2.repository.mission.MissionRetriever;
import com.sopkaton.web2.repository.team.Team;
import com.sopkaton.web2.repository.team.TeamRetriever;
import com.sopkaton.web2.repository.user.User;
import com.sopkaton.web2.repository.user.UserRetriever;
import com.sopkaton.web2.repository.userteam.UserTeam;
import com.sopkaton.web2.repository.userteam.UserTeamRetriever;
import com.sopkaton.web2.service.command.CardCreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final UserRetriever userRetriever;
    private final MissionRetriever missionRetriever;
    private final TeamRetriever teamRetriever;
    private final UserTeamRetriever userTeamRetriever;

    public void createCard(CardCreateCommand from) {
        User userById;
        try {
             userById = userRetriever.findUserById(from.userId());
        } catch (CoreException e) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        long count = missionRetriever.getCount();
        long randomMissionId = (long) (Math.random() * count) + 1;
        Mission randomMission;
        try {
            randomMission = missionRetriever.findMissionById(randomMissionId);
        } catch (CoreException e) {
            throw new CustomException(ErrorCode.MISSION_NOT_FOUND);
        }

        Team team;
        try {
            team = teamRetriever.findTeamById(from.groupId());
        } catch (CoreException e) {
            throw new CustomException(ErrorCode.TEAM_NOT_FOUND);
        }

        UserTeam userTeam;
        try {
            userTeam = userTeamRetriever.findUserTeamByUserAndTeam(userById, team);
        } catch (CoreException e) {
            throw new CustomException(ErrorCode.USER_TEAM_NOT_FOUND);
        }
        cardRepository.save(Card.builder()
                .userTeam(userTeam)
                .team(team)
                .mission(randomMission)
                .hint(from.hint())
                .mission(randomMission)
                .build());
    }
}
