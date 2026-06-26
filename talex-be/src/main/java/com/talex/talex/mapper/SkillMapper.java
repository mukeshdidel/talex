package com.talex.talex.mapper;

import com.talex.talex.dto.res.OfferedSkillPostResponse;
import com.talex.talex.entity.UserSkillOffered;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SkillMapper {

    @Mapping(target = "id", source = "skillOffered.id")
    @Mapping(target = "skillId", source = "skillOffered.skill.id")
    @Mapping(target = "skillName", source = "skillOffered.skill.name")
    @Mapping(target = "proficiencyLevel", source = "skillOffered.proficiencyLevel")
    @Mapping(target = "yearsExperience", source = "skillOffered.yearsExperience")
    OfferedSkillPostResponse toOfferedSkillPostResponse(UserSkillOffered skillOffered);

}
