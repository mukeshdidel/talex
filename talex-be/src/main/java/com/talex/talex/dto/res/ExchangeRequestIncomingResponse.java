package com.talex.talex.dto.res;

import com.talex.talex.entity.ExchangeRequestStatus;
import lombok.Builder;

@Builder
public record ExchangeRequestIncomingResponse(
        Long requestId,
        Long senderId,
        String senderUsername,
        String senderName,
        OfferedSkillPostResponse senderSkillOffered,
        OfferedSkillPostResponse receiverSkillOffered,
        String message,
        ExchangeRequestStatus status
) {
}
