package com.sopkaton.web2.service.response;

import java.util.List;

public record TeamsResponse(List<TeamResponse> teams) {
    public static TeamsResponse of(List<TeamResponse> teams) {
        return new TeamsResponse(teams);
    }
}
