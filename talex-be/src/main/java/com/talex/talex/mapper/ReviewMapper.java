package com.talex.talex.mapper;

import com.talex.talex.dto.res.ReviewResponse;
import com.talex.talex.entity.SessionReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {

    @Mapping(target = "reviewId", source = "id")
    @Mapping(target = "sessionId", source = "session.id")
    @Mapping(target = "reviewerId", source = "reviewer.id")
    @Mapping(target = "reviewerUsername", source = "reviewer.username")
    @Mapping(target = "reviewedUserId", source = "reviewedUser.id")
    @Mapping(target = "reviewedUsername", source = "reviewedUser.username")
    ReviewResponse toReviewResponse(SessionReview review);
}
