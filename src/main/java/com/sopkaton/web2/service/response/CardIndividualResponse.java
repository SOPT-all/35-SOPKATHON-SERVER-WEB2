package com.sopkaton.web2.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sopkaton.web2.repository.card.Card;
import com.sopkaton.web2.repository.mission.Mission;
import com.sopkaton.web2.repository.user.User;
import lombok.ToString.Include;

public record CardIndividualResponse(long id,
                                     String hint,
                                     @JsonInclude(value = JsonInclude.Include.NON_NULL) String phoneNumber,
                                     @JsonInclude(value = JsonInclude.Include.NON_NULL)
                                     String mission) {
    public static CardIndividualResponse createHidden(Card card) {
        return new CardIndividualResponse(card.getId(), card.getHint(), null, null);
    }

    public static CardIndividualResponse createVisible(Card card, Mission mission, User user) {
        return new CardIndividualResponse(card.getId(), card.getHint(), user.getPhoneNumber(), mission.getContent());
    }
}
