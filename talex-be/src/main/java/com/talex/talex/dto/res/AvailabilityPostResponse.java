package com.talex.talex.dto.res;

import lombok.Builder;

import java.time.LocalTime;


@Builder
public record AvailabilityPostResponse(
        Long id,
        Long dayOfWeek,
        LocalTime StartTime,
        LocalTime EndTime
) {
}
