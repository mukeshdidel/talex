package com.talex.talex.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalTime createdAt;

    @OneToMany(
            mappedBy = "conversation",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ConversationParticipant> participants = new HashSet<>();

}
