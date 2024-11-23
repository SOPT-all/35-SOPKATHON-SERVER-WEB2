package com.sopkaton.web2.service;

import com.sopkaton.web2.common.CoreException;
import com.sopkaton.web2.common.CustomException;
import com.sopkaton.web2.common.ErrorCode;
import com.sopkaton.web2.repository.Card;
import com.sopkaton.web2.repository.CardRepository;
import com.sopkaton.web2.repository.Mission;
import com.sopkaton.web2.repository.MissionRetriever;
import com.sopkaton.web2.repository.Team;
import com.sopkaton.web2.repository.TeamRetriever;
import com.sopkaton.web2.repository.User;
import com.sopkaton.web2.repository.UserRetriever;
import com.sopkaton.web2.repository.UserTeam;
import com.sopkaton.web2.repository.UserTeamRetriever;
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
        //1. 카드를 만든다.
        //1. userretrieve에서 유저의 핸드폰 번호
        //2. 미션에서 random 미션
        //3. 작성한 힌트.
        //4. team 창아오고
        //5. userteam 찾아와야한다.
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
