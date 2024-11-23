package com.sopkaton.web2.controller;

import com.sopkaton.web2.common.api.SuccessCode;
import com.sopkaton.web2.controller.request.RegisterRequest;
import com.sopkaton.web2.controller.request.TeamDuplicateRequest;
import com.sopkaton.web2.service.response.TeamDuplicateResponse;
import com.sopkaton.web2.service.response.TeamResponse;
import com.sopkaton.web2.service.response.TeamsResponse;
import com.sopkaton.web2.service.team.TeamService;
import com.sopkaton.web2.controller.request.TeamCreateRequest;
import com.sopkaton.web2.service.response.TeamCreateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping()
    ResponseEntity<ApiResponse<TeamCreateResponse>> createTeam(
            @RequestHeader("User-Id") long userId,
            @Valid @RequestBody TeamCreateRequest teamCreateRequest) {
        TeamCreateResponse teamCreateResponse = teamService.createTeam(userId, teamCreateRequest);
        return ResponseEntity.ok(ApiResponse.success(SuccessCode.CREATED, teamCreateResponse));
        }

    @GetMapping
    public ApiResponse<TeamsResponse> findTeams(@RequestParam String name) {
        return ApiResponse.success(SuccessCode.OK, teamService.findTeamsByName(name));
    }


    @PostMapping("/{teamId}/register")
    public ApiResponse<TeamResponse> registerTeam(@PathVariable long teamId, @RequestHeader("User-Id") long userId, @RequestBody RegisterRequest request) {
        return ApiResponse.success(SuccessCode.OK, teamService.registerTeam(teamId, userId, request.code()));
    }

    @GetMapping("/check")
    public ApiResponse<TeamDuplicateResponse> checkDuplicateTeam(@RequestBody TeamDuplicateRequest teamDuplicateRequest) {
        TeamDuplicateResponse teamDuplicateResponse = teamService.checkDuplicate(teamDuplicateRequest.name());
        return ApiResponse.success(SuccessCode.OK, teamDuplicateResponse);
    }

}
