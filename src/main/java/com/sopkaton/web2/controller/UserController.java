package com.sopkaton.web2.controller;


import com.sopkaton.web2.common.api.SuccessCode;
import com.sopkaton.web2.controller.request.UserPostRequest;
import com.sopkaton.web2.service.UserService;
import com.sopkaton.web2.service.response.IdResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @PostMapping("/signin")
    ResponseEntity<ApiResponse<IdResponse>> signIn(
            @Valid @RequestBody UserPostRequest userPostRequest
    ) {
            Long userId = userService.createUser(userPostRequest);
            return ResponseEntity.ok(ApiResponse.success(SuccessCode.OK,IdResponse.of(userId)));
    }




}
