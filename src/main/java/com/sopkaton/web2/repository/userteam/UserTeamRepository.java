package com.sopkaton.web2.repository.userteam;

import com.sopkaton.web2.repository.team.Team;
import com.sopkaton.web2.repository.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTeamRepository extends JpaRepository<UserTeam, Long> {
    Optional<UserTeam> findUserTeamByUserAndTeam(User user, Team team);
}
