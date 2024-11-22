package com.sopkaton.web2.controller;

import com.sopkaton.web2.common.SuccessCode;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/health")
public class HealthCheckController {
        @RequestMapping("/check")
        public ApiResponse<String> check() {
            return ApiResponse.success(SuccessCode.OK, "OK");
        }
}
