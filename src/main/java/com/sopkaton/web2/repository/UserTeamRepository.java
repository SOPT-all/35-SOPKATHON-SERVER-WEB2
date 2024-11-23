package com.sopkaton.web2.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTeamRepository extends JpaRepository<UserTeam, Long> {
    Optional<UserTeam> findUserTeamByUserAndTeam(User user, Team team);
}
