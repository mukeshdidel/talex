package com.talex.talex.mapper;

import com.talex.talex.dto.res.MatchResponse;
import com.talex.talex.dto.res.MatchSuggestResponse;
import com.talex.talex.entity.Match;
import com.talex.talex.entity.User;
import org.mapstruct.Context;
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



    default boolean isUser1(Match match, User currentUser) {
        return match.getUser1().getId().equals(currentUser.getId());
    }

    @Mapping(target = "matchId", source = "match.id")
    @Mapping(target = "otherUserId", expression = "java(isUser1(match, currentUser) ? match.getUser2().getId() : match.getUser1().getId())")
    @Mapping(target = "otherUsername", expression = "java(isUser1(match, currentUser) ? match.getUser2().getUsername() : match.getUser1().getUsername())")
    @Mapping(target = "otherName", expression = "java(isUser1(match, currentUser) ? match.getUser2().getName() : match.getUser1().getName())")
    @Mapping(target = "otherProfilePicture", expression = "java(isUser1(match, currentUser) ? match.getUser2().getProfilePicture() : match.getUser1().getProfilePicture())")
    @Mapping(target = "theyTeach", expression = "java(isUser1(match, currentUser) ? match.getUser2SkillOffered().getSkill().getName() : match.getUser1SkillOffered().getSkill().getName())")
    @Mapping(target = "proficiencyLevel", expression = "java(isUser1(match, currentUser) ? match.getUser2SkillOffered().getProficiencyLevel() : match.getUser1SkillOffered().getProficiencyLevel())")
    @Mapping(target = "yearsExperience", expression = "java(isUser1(match, currentUser) ? match.getUser2SkillOffered().getYearsExperience() : match.getUser1SkillOffered().getYearsExperience())")
    @Mapping(target = "iTeach", expression = "java(isUser1(match, currentUser) ? match.getUser1SkillOffered().getSkill().getName() : match.getUser2SkillOffered().getSkill().getName())")
    @Mapping(target = "status", source = "match.status")
    MatchResponse toMatchResponse(Match match, @Context  User currentUser);

    List<MatchResponse> toMatchResponseList(List<Match> matches, @Context User currentUser);

}
