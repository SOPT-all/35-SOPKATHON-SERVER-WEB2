package com.sopkaton.web2.controller;

import com.sopkaton.web2.common.api.SuccessCode;
import com.sopkaton.web2.controller.request.CardRequest;
import com.sopkaton.web2.service.CardService;
import com.sopkaton.web2.service.command.CardCreateCommand;
import com.sopkaton.web2.service.response.CardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
@Slf4j
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ApiResponse<Void> createCard(@RequestHeader("User-Id")long userId, @RequestBody @Valid CardRequest request) {
        cardService.createCard(CardCreateCommand.from(request.groupId(), request.hint(), userId));
        return ApiResponse.success(SuccessCode.CREATED);
    }

    @GetMapping("/{cardId}")
    public ApiResponse<CardResponse> findCard (@PathVariable long cardId,
                                               @RequestParam("teamId")long teamId,
                                               @RequestHeader("User-Id")long userId){
        log.info(String.valueOf(cardId));
        log.info(String.valueOf(teamId));
        log.info(String.valueOf(userId));
        return ApiResponse.success(SuccessCode.OK, cardService.findCard(cardId, teamId, userId));
    }
}
