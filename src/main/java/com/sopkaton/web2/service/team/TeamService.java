package com.sopkaton.web2.service.team;

import com.sopkaton.web2.common.api.SuccessCode;
import com.sopkaton.web2.repository.team.Team;
import com.sopkaton.web2.repository.team.TeamRetriever;
import com.sopkaton.web2.service.response.TeamResponse;
import com.sopkaton.web2.service.response.TeamsResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRetriever retriever;

    public TeamsResponse findTeamsByName(String name) {
        List<TeamResponse> list = retriever.findTeamsByName(name).stream().map(TeamResponse::of)
                .collect(Collectors.toList());

        return TeamsResponse.of(list);
    }
}
