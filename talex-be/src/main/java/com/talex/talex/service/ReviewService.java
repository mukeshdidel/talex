package com.talex.talex.service;

import com.talex.talex.dto.req.ReviewPostRequest;
import com.talex.talex.dto.res.ReviewResponse;
import com.talex.talex.entity.Match;
import com.talex.talex.entity.Session;
import com.talex.talex.entity.SessionReview;
import com.talex.talex.entity.SessionStatus;
import com.talex.talex.entity.User;
import com.talex.talex.mapper.ReviewMapper;
import com.talex.talex.repo.SessionRepo;
import com.talex.talex.repo.SessionReviewRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ReviewService {

    public final UserService userService;
    public final SessionRepo sessionRepo;
    public final SessionReviewRepo sessionReviewRepo;
    public final NotificationService notificationService;
    public final ReviewMapper reviewMapper;

    @Transactional
    public ReviewResponse createReview(String username, ReviewPostRequest request) {
        User reviewer = userService.getUserByUsername(username);
        Session session = sessionRepo.findById(request.sessionId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Session not found"
                ));

        User reviewedUser = getReviewedUser(session.getMatch(), reviewer);

        if (session.getStatus() != SessionStatus.completed) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Reviews can only be added after the session is completed"
            );
        }

        if (sessionReviewRepo.existsBySession_IdAndReviewer_Id(session.getId(), reviewer.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "You have already reviewed this session"
            );
        }

        SessionReview review = SessionReview.builder()
                .session(session)
                .reviewer(reviewer)
                .reviewedUser(reviewedUser)
                .rating(request.rating())
                .comment(request.comment())
                .build();

        review = sessionReviewRepo.save(review);

        notificationService.notify(
                reviewedUser,
                "New review",
                reviewer.getName() + " reviewed your completed session."
        );

        return reviewMapper.toReviewResponse(review);
    }

    private User getReviewedUser(Match match, User reviewer) {
        if (match.getUser1().getId().equals(reviewer.getId())) {
            return match.getUser2();
        }

        if (match.getUser2().getId().equals(reviewer.getId())) {
            return match.getUser1();
        }

        throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                "You are not allowed to review this session"
        );
    }
}
