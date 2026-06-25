package com.talex.talex.repo;

import com.talex.talex.entity.UserSkillWanted;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSkillWantedRepo extends JpaRepository<UserSkillWanted, Long> {
    void deleteBySkill_IdAndUser_Username(Long skillId, String username);
}
