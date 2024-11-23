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
import com.sopkaton.web2.service.response.CardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final UserRetriever userRetriever;
    private final MissionRetriever missionRetriever;
    private final TeamRetriever teamRetriever;
    private final UserTeamRetriever userTeamRetriever;

    @Transactional
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

    @Transactional
    public CardResponse findCard(long cardId, long teamId, long userId) {

        //userteam에서 하나를 찾는다.
        //찬스가 없는 경우 에러를 던진다.
        //찬스가 잇다면 찬스를 차감하고 카드에 대한 응답을 내려준다,
        //카드에 checkedby에 userteam의 pk를 넣어준다.
        UserTeam userTeam = userTeamRetriever.findUserTeamByUserIdAndTeamId(userId, teamId);
        if (userTeam.getChance() == false) {// 찬스가 없다면
            throw new CustomException(ErrorCode.NO_CHANCE);
        }

        Card card = cardRepository.findById(cardId).orElseThrow(() -> new CustomException(ErrorCode.CARD_NOT_FOUND));
        card.setCheckedBy(userTeam.getId());
        String phoneNumber = card.getUserTeam().getUser().getPhoneNumber();
        userTeam.setChance(false);
        return new CardResponse(card.getHint(), phoneNumber,card.getMission().getContent());
    }
}
