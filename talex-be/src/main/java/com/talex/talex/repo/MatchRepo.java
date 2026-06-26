package com.talex.talex.repo;

import com.talex.talex.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepo extends JpaRepository<Match, Long> {
}
