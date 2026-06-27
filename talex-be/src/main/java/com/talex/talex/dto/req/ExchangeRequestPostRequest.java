package com.talex.talex.dto.req;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ExchangeRequestPostRequest(

        @NotNull(message = "Receiver id is required")
        Long receiverId,

        @NotNull(message = "Sender skill id is required")
        Long senderSkillOfferId,

        @NotNull(message = "Receiver skill id is required")
        Long receiverSkillOfferId,

        @Size(max = 1000, message = "Message must be 1000 characters or less")
        String message
) {
}
