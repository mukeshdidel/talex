package com.talex.talex.repo;

import com.talex.talex.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    @Query("""
SELECT DISTINCT u
FROM User u
WHERE u.id <> :userId
AND EXISTS (
    SELECT 1
    FROM UserSkillOffered uso
    WHERE uso.user = u
      AND uso.skill.id IN (
          SELECT usw.skill.id
          FROM UserSkillWanted usw
          WHERE usw.user.id = :userId
      )
)
AND EXISTS (
    SELECT 1
    FROM UserSkillWanted usw
    WHERE usw.user = u
      AND usw.skill.id IN (
          SELECT uso.skill.id
          FROM UserSkillOffered uso
          WHERE uso.user.id = :userId
      )
)
""")
    List<User> findMatchSuggestions(Long userId);

    @Query("""
SELECT DISTINCT u
FROM User u
LEFT JOIN FETCH u.skillsOffered uso
LEFT JOIN FETCH uso.skill
LEFT JOIN FETCH u.skillsWanted usw
LEFT JOIN FETCH usw.skill
LEFT JOIN FETCH u.availabilities a
WHERE u.id <> :currentUserId
AND (
    :skill IS NULL OR
    LOWER(uso.skill.name) LIKE LOWER(CONCAT('%', :skill, '%')) OR
    LOWER(usw.skill.name) LIKE LOWER(CONCAT('%', :skill, '%'))
)
AND (
    :category IS NULL OR
    LOWER(uso.skill.category) LIKE LOWER(CONCAT('%', :category, '%')) OR
    LOWER(usw.skill.category) LIKE LOWER(CONCAT('%', :category, '%'))
)
AND (
    :availability IS NULL OR
    a.dayOfWeek = :availability
)
AND (
    :username IS NULL OR
    LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%'))
)
""")
    List<User> searchUsers(
            Long currentUserId,
            String skill,
            String category,
            Long availability,
            String username
    );

}
