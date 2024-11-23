package com.sopkaton.web2.repository.team;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByNameIgnoreCaseContainingOrderByName(String name);
}
