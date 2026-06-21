package com.talex.talex.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class ExchangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sender_Skill_id", nullable = false)
    private Skill senderSkill;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "receiver_Skill_id", nullable = false)
    private Skill receiverSkill;

    private ExchangeRequestStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;


}
