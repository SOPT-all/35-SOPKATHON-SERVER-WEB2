package com.sopkaton.web2.controller;

import com.sopkaton.web2.common.SuccessCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/health")
@RestController
public class HealthCheckController {
        @GetMapping("/check")
        public ApiResponse<String> check() {
            return ApiResponse.success(SuccessCode.OK, "OK");
        }
}
