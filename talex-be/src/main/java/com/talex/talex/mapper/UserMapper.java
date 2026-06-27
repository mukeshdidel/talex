package com.talex.talex.mapper;


import com.talex.talex.dto.req.AvailabilityPostRequest;
import com.talex.talex.dto.req.OfferedSkillPostRequest;
import com.talex.talex.dto.req.SignupRequest;
import com.talex.talex.dto.req.WantedSkillPostRequest;
import com.talex.talex.dto.res.*;
import com.talex.talex.entity.*;
import jakarta.validation.Valid;
import org.hibernate.annotations.CreationTimestamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toUser(SignupRequest request);

    SignupResponse toSignupResponse(User user);

    @Mapping(target = "token", source = "token")
    LoginResponse toLoginResponse(User user, String token);

//    @Mapping()
    @Mapping(target = "skillsOffered", source = "user.skillsOffered")
    @Mapping(target = "skillsWanted", source = "user.skillsWanted")
    @Mapping(target = "availabilities", source = "user.availabilities")
    MeResponse toMeResponse(User user);

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "skillsOffered", source = "skillsOffered")
    @Mapping(target = "skillsWanted", source = "skillsWanted")
    @Mapping(target = "availabilities", source = "availabilities")
    UserSearchResponse toUserSearchResponse(User user);

    List<UserSearchResponse> toUserSearchResponseList(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "skill", source = "skill")
    @Mapping(target = "proficiencyLevel", source = "request.proficiencyLevel")
    @Mapping(target = "yearsExperience", source = "request.yearsExperience")
    UserSkillOffered toUserSkillOffered(OfferedSkillPostRequest request, User user, Skill skill);

    @Mapping(target = "id", source = "skillOffered.id")
    @Mapping(target = "proficiencyLevel", source = "skillOffered.proficiencyLevel")
    @Mapping(target = "yearsExperience", source = "skillOffered.yearsExperience")
    @Mapping(target = "skillId", source = "skillOffered.skill.id")
    @Mapping(target = "skillName", source = "skillOffered.skill.name")
    OfferedSkillPostResponse toOfferedSkillPostResponse(UserSkillOffered skillOffered);

    List<OfferedSkillPostResponse> toOfferedSkillPostResponseList(
            List<UserSkillOffered> skillOfferedList
    );

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "skill", source = "skill")
    @Mapping(target = "priority", source = "request.priority")
    UserSkillWanted toUserSkillWanted(WantedSkillPostRequest request, User user, Skill skill);

    @Mapping(target = "id", source = "skillOffered.id")
    @Mapping(target = "priority", source = "skillOffered.priority")
    @Mapping(target = "skillId", source = "skillOffered.skill.id")
    @Mapping(target = "skillName", source = "skillOffered.skill.name")
    WantedSkillPostResponse toWantedSkillPostResponse(UserSkillWanted skillOffered);

    List<WantedSkillPostResponse> toWantedSkillPostResponseList(
            List<UserSkillWanted> skillWantedList
    );


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "dayOfWeek", source = "request.dayOfWeek")
    @Mapping(target = "startTime", source = "request.startTime")
    @Mapping(target = "endTime", source = "request.endTime")
    Availability toAvailability(@Valid AvailabilityPostRequest request, User user);

    @Mapping(target = "id", source = "availability.id")
    @Mapping(target = "dayOfWeek", source = "availability.dayOfWeek")
    @Mapping(target = "startTime", source = "availability.startTime")
    @Mapping(target = "endTime", source = "availability.endTime")
    AvailabilityPostResponse toAvailabilityPostResponse(Availability availability);
}
