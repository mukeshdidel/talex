package com.talex.talex.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user1_id", nullable = false)
    private User user1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user2_id", nullable = false)
    private User user2;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user1_Skill_id", nullable = false)
    private Skill user1Skill;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user2_Skill_id", nullable = false)
    private Skill user2Skill;

    private MatchStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
