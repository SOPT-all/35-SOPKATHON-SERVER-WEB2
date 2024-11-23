package com.sopkaton.web2.repository.team;

import com.sopkaton.web2.repository.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Table(name = "team")
public class Team extends BaseTimeEntity {

    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "minimum_start_number", nullable = false)
    private int minimumStartNumber;

    public Team(final String name, final int minimumStartNumber) {
        this.name = name;
        this.code = UUID.randomUUID().toString();
        this.minimumStartNumber = minimumStartNumber;
    }

    @Column(name = "current_number", nullable = false)
    private int currentNumber;

    public void addCurrentNumber() {
        this.currentNumber++;
    }
}
