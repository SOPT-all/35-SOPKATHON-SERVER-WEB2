package com.sopkaton.web2.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

public record CardsResponse(boolean isAbleToChoose, List<CardIndividualResponse> hiddenList,
                            @JsonInclude(value = JsonInclude.Include.NON_NULL)CardIndividualResponse myCard) {
}
