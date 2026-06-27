package com.talex.talex.service;

import com.talex.talex.dto.req.SessionPostRequest;
import com.talex.talex.dto.res.SessionResponse;
import com.talex.talex.entity.Match;
import com.talex.talex.entity.Session;
import com.talex.talex.entity.SessionStatus;
import com.talex.talex.entity.User;
import com.talex.talex.mapper.SessionMapper;
import com.talex.talex.repo.MatchRepo;
import com.talex.talex.repo.SessionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    public final UserService userService;
    public final MatchRepo matchRepo;
    public final NotificationService notificationService;
    public final SessionRepo sessionRepo;
    public final SessionMapper sessionMapper;

    @Transactional
    public SessionResponse createSession(String username, SessionPostRequest request) {
        User currentUser = userService.getUserByUsername(username);
        Match match = getMatchForUser(request.matchId(), currentUser);

        Session session = Session.builder()
                .match(match)
                .host(currentUser)
                .scheduledAt(request.scheduledAt())
                .durationMinutes(request.durationMinutes())
                .meetingLink(request.meetingLink())
                .status(SessionStatus.scheduled)
                .build();

        session = sessionRepo.save(session);

        User otherUser = getOtherParticipant(match, currentUser);
        notificationService.notify(
                otherUser,
                "Session booked",
                currentUser.getName() + " booked a session with you."
        );

        return sessionMapper.toSessionResponse(session, currentUser);
    }

    @Transactional(readOnly = true)
    public List<SessionResponse> getSessions(String username) {
        User currentUser = userService.getUserByUsername(username);
        List<Session> sessions = sessionRepo.findSessionsByUserId(currentUser.getId());

        return sessionMapper.toSessionResponseList(sessions, currentUser);
    }

    @Transactional(readOnly = true)
    public SessionResponse getSession(String username, Long sessionId) {
        User currentUser = userService.getUserByUsername(username);
        Session session = getSessionForUser(sessionId, currentUser);

        return sessionMapper.toSessionResponse(session, currentUser);
    }

    @Transactional
    public SessionResponse cancelSession(String username, Long sessionId) {
        User currentUser = userService.getUserByUsername(username);
        Session session = getSessionForUser(sessionId, currentUser);

        if (session.getStatus() == SessionStatus.cancelled) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Session is already cancelled"
            );
        }

        if (session.getStatus() == SessionStatus.completed) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Completed sessions cannot be cancelled"
            );
        }

        session.setStatus(SessionStatus.cancelled);
        session = sessionRepo.save(session);

        User otherUser = getOtherParticipant(session.getMatch(), currentUser);
        notificationService.notify(
                otherUser,
                "Session cancelled",
                currentUser.getName() + " cancelled your session."
        );

        return sessionMapper.toSessionResponse(session, currentUser);
    }

    @Transactional
    public SessionResponse completeSession(String username, Long sessionId) {
        User currentUser = userService.getUserByUsername(username);
        Session session = getSessionForUser(sessionId, currentUser);

        if (session.getStatus() == SessionStatus.completed) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Session is already completed"
            );
        }

        if (session.getStatus() == SessionStatus.cancelled) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cancelled sessions cannot be completed"
            );
        }

        session.setStatus(SessionStatus.completed);
        session = sessionRepo.save(session);

        User otherUser = getOtherParticipant(session.getMatch(), currentUser);
        notificationService.notify(
                otherUser,
                "Session completed",
                currentUser.getName() + " marked your session as completed."
        );

        return sessionMapper.toSessionResponse(session, currentUser);
    }

    private Session getSessionForUser(Long sessionId, User currentUser) {
        Session session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Session not found"
                ));

        validateMatchParticipant(session.getMatch(), currentUser);

        return session;
    }

    private Match getMatchForUser(Long matchId, User currentUser) {
        Match match = matchRepo.findById(matchId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Match not found"
                ));

        validateMatchParticipant(match, currentUser);

        return match;
    }

    private void validateMatchParticipant(Match match, User currentUser) {
        boolean isParticipant = match.getUser1().getId().equals(currentUser.getId())
                || match.getUser2().getId().equals(currentUser.getId());

        if (!isParticipant) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You are not allowed to access this match"
            );
        }
    }

    private User getOtherParticipant(Match match, User currentUser) {
        if (match.getUser1().getId().equals(currentUser.getId())) {
            return match.getUser2();
        }

        return match.getUser1();
    }
}
