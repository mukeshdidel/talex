package com.talex.talex.repo;

import com.talex.talex.entity.ExchangeRequest;
import com.talex.talex.entity.ExchangeRequestStatus;
import com.talex.talex.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExchangeRequestRepo extends JpaRepository<ExchangeRequest, Long> {
    List<ExchangeRequest> findByReceiver(User user);
    List<ExchangeRequest> findBySender(User user);
    Optional<ExchangeRequest> findByIdAndReceiver(Long id, User user);

    boolean existsBySender_IdAndReceiver_IdAndStatus(
            Long senderId,
            Long receiverId,
            ExchangeRequestStatus status
    );
}
