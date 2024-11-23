package com.sopkaton.web2.repository.card;

import com.sopkaton.web2.repository.BaseTimeEntity;
import com.sopkaton.web2.repository.mission.Mission;
import com.sopkaton.web2.repository.team.Team;
import com.sopkaton.web2.repository.userteam.UserTeam;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Table(name = "card")
@Builder
@AllArgsConstructor
public class Card extends BaseTimeEntity {

    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne
    @JoinColumn(name = "user_group_id")
    private UserTeam userTeam;

    @Column(name = "hint", nullable = false)
    private String hint;

    @Column(name = "checked_by", nullable = true)
    private Long checkedBy;
}
