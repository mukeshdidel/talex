package com.talex.talex.repo;

import com.talex.talex.entity.ExchangeRequest;
import com.talex.talex.entity.ExchangeRequestStatus;
import com.talex.talex.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExchangeRequestRepo extends JpaRepository<ExchangeRequest, Long> {
    List<ExchangeRequest> findByReceiver(User user);
    List<ExchangeRequest> findBySender(User user);
    Optional<ExchangeRequest> findByIdAndReceiver(Long id, User user);

    @Query("""
    SELECT COUNT(er) > 0
    FROM ExchangeRequest er
    WHERE er.status = :status
    AND (
        (er.sender.id = :senderId AND er.receiver.id = :receiverId)
        OR (er.sender.id = :receiverId AND er.receiver.id = :senderId)
    )
    """)
    boolean existsPendingBetweenUsers(
            Long senderId,
            Long receiverId,
            ExchangeRequestStatus status
    );
}
