package com.sopkaton.web2.repository.card;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByTeamId(long teamId);



    Optional<Card> findByCheckedByAndTeamId(Long id, long teamId);
}
