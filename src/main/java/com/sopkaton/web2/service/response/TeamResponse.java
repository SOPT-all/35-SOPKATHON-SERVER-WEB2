package com.sopkaton.web2.service.response;

import com.sopkaton.web2.repository.team.Team;

public record TeamResponse(Long id, String name) {
    public static TeamResponse of(Team t) {
        return new TeamResponse(t.getId(), t.getName());
    }
}
