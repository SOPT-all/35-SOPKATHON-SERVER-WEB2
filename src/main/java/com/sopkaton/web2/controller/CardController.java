package com.sopkaton.web2.controller;

import com.sopkaton.web2.common.SuccessCode;
import com.sopkaton.web2.controller.request.CardRequest;
import com.sopkaton.web2.service.CardService;
import com.sopkaton.web2.service.command.CardCreateCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ApiResponse<Void> createCard(@RequestHeader("User-Id")long userId, @RequestBody @Valid CardRequest request) {
        cardService.createCard(CardCreateCommand.from(request.groupId(), request.hint(), userId));
        return ApiResponse.success(SuccessCode.CREATED);
    }
}
