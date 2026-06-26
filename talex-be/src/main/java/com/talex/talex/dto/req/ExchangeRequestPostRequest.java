package com.talex.talex.dto.req;

import jakarta.validation.constraints.NotNull;

public record ExchangeRequestPostRequest(

        @NotNull(message = "Receiver id is required")
        Long receiverId,

        @NotNull(message = "Sender skill id is required")
        Long senderSkillOfferId,

        @NotNull(message = "Receiver skill id is required")
        Long receiverSkillOfferId,
        String message
) {
}
