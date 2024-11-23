package com.sopkaton.web2.repository.userteam;

import com.sopkaton.web2.repository.BaseTimeEntity;
import com.sopkaton.web2.repository.team.Team;
import com.sopkaton.web2.repository.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Table(name = "user_team")
public class UserTeam extends BaseTimeEntity {

    @Id
    @Column(name = "user_team_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "chance", nullable = false)
    private Boolean chance;

    public void setChance(boolean b) {
        this.chance = b;
    }

    @Builder
    public UserTeam(User user, Team team, Boolean chance) {
        this.user = user;
        this.team = team;
        this.chance = chance;
    }
}
