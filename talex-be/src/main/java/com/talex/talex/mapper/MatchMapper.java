package com.talex.talex.mapper;

import com.talex.talex.dto.res.MatchSuggestResponse;
import com.talex.talex.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = UserMapper.class)
public interface MatchMapper {

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "teaches", source = "skillsOffered")
    @Mapping(target = "wants", source = "skillsWanted")
    MatchSuggestResponse toMatchSuggestResponse(User user);

    List<MatchSuggestResponse> toMatchSuggestResponseList(List<User> users);
}
