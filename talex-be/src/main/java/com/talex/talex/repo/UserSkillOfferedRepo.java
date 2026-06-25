package com.talex.talex.repo;

import com.talex.talex.entity.UserSkillOffered;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSkillOfferedRepo extends JpaRepository<UserSkillOffered, Long> {

    void deleteBySkill_IdAndUser_Username(
            Long skillId,
            String username
    );

}
