package com.sopkaton.web2.repository;

import com.sopkaton.web2.common.TeamNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeamRetriever {
    private final TeamRepository teamRepository;


    public Team findTeamById(long randomTeamId) {
        return teamRepository.findById(randomTeamId).orElseThrow(TeamNotFoundException::new);
    }
}
