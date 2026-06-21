package com.talex.talex.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    private Set<UserSkillOffered> skillsOffered;


    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    private Set<UserSkillWanted> skillsWanted;

}
