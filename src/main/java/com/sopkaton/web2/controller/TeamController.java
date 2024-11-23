package com.sopkaton.web2.controller;

import com.sopkaton.web2.common.api.SuccessCode;
import com.sopkaton.web2.controller.request.RegisterRequest;
import com.sopkaton.web2.service.response.TeamResponse;
import com.sopkaton.web2.service.response.TeamsResponse;
import com.sopkaton.web2.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping
    public ApiResponse<TeamsResponse> findTeams(@RequestParam String name) {
        return ApiResponse.success(SuccessCode.OK, teamService.findTeamsByName(name));
    }

    @GetMapping("/{teamId}/register")
    public ApiResponse<TeamResponse> registerTeam(@RequestParam long teamId, @RequestHeader("User-Id") long userId, @RequestBody RegisterRequest request) {
        return ApiResponse.success(SuccessCode.OK, teamService.registerTeam(teamId, userId, request.code()));
    }

}
