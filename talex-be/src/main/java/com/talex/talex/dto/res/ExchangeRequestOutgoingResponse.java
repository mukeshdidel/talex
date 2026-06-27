package com.talex.talex.dto.res;

import com.talex.talex.entity.ExchangeRequestStatus;

public record ExchangeRequestOutgoingResponse(
        Long requestId,
        Long receiverId,
        String receiverUsername,
        String receiverName,
        OfferedSkillPostResponse senderSkillOffered,
        OfferedSkillPostResponse receiverSkillOffered,
        String message,
        ExchangeRequestStatus status
) {
}
