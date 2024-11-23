package com.sopkaton.web2.repository.team;

import com.sopkaton.web2.common.api.CustomException;
import com.sopkaton.web2.common.api.ErrorCode;
import com.sopkaton.web2.common.core.TeamNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeamRetriever {
    private final TeamRepository teamRepository;


    public Team findTeamById(long randomTeamId) {
        return teamRepository.findById(randomTeamId).orElseThrow(() -> new CustomException(ErrorCode.TEAM_NOT_FOUND));
    }

    public List<Team> findTeamsByName(String name) {
        return teamRepository.findAllByNameIgnoreCaseContainingOrderByName(name);
    }
}

