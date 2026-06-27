package com.talex.talex.repo;

import com.talex.talex.entity.SessionReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionReviewRepo extends JpaRepository<SessionReview, Long> {

    boolean existsBySession_IdAndReviewer_Id(Long sessionId, Long reviewerId);
}
