package com.talex.talex.dto.res;

import com.talex.talex.entity.ExchangeRequestStatus;

public record ExchangeRequestPostResponse(
        Long requestId,
        ExchangeRequestStatus status
) {
}
