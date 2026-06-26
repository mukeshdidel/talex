package com.talex.talex.service;

import com.talex.talex.dto.req.AvailabilityPostRequest;
import com.talex.talex.dto.req.MePutRequest;
import com.talex.talex.dto.req.OfferedSkillPostRequest;
import com.talex.talex.dto.req.WantedSkillPostRequest;
import com.talex.talex.dto.res.AvailabilityPostResponse;
import com.talex.talex.dto.res.MeResponse;
import com.talex.talex.dto.res.OfferedSkillPostResponse;
import com.talex.talex.dto.res.WantedSkillPostResponse;
import com.talex.talex.entity.*;
import com.talex.talex.mapper.UserMapper;
import com.talex.talex.repo.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    // repo
    public final UserRepo userRepo;
    public final SkillRepo skillRepo;
    public final UserSkillOfferedRepo userSkillOfferedRepo;
    public final UserSkillWantedRepo userSkillWantedRepo;
    public final AvailabilityRepo availabilityRepo;

    // mapper
    public final UserMapper userMapper;


    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("user not found."));
    }

    public User getUserById(Long aLong) {
        return userRepo.findById(aLong)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));
    }

    public MeResponse getMe(String username) {
        User user = getUserByUsername(username);
        return userMapper.toMeResponse(user);

    }

    public MeResponse putMe(String username, MePutRequest request) {
        User user = getUserByUsername(username);
        user.setBio(request.bio());
        user.setName(request.name());

        userRepo.save(user);

        return userMapper.toMeResponse(user);
    }

    public void updateProfilePicture(String username, String url) {
        User user = getUserByUsername(username);
        user.setProfilePicture(url);
        userRepo.save(user);
    }


    public OfferedSkillPostResponse addOfferedSkill(OfferedSkillPostRequest request, String username) {
        User user = getUserByUsername(username);
        Skill skill = skillRepo.findById(request.skillId())
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Skill not found"
                ));
        UserSkillOffered skillOffered = userMapper.toUserSkillOffered(request, user, skill);
        skillOffered = userSkillOfferedRepo.save(skillOffered);
        return userMapper.toOfferedSkillPostResponse(skillOffered);
    }


    public void deleteOfferedSkill(String username, Long skillId) {
        userSkillOfferedRepo.deleteBySkill_IdAndUser_Username(skillId, username);
    }


    public WantedSkillPostResponse addWantedSkill(WantedSkillPostRequest request, String username) {
        User user = getUserByUsername(username);
        Skill skill = skillRepo.findById(request.skillId())
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Skill not found"
                ));
        UserSkillWanted skillWanted = userMapper.toUserSkillWanted(request, user, skill);
        skillWanted = userSkillWantedRepo.save(skillWanted);

        return userMapper.toWantedSkillPostResponse(skillWanted);
    }

    public void deleteWantedSkill(String username, Long skillId) {
        userSkillWantedRepo.deleteBySkill_IdAndUser_Username(skillId, username);
    }

    public AvailabilityPostResponse addAvailability(@Valid AvailabilityPostRequest request, String username) {

        User user = getUserByUsername(username);

        Availability availability = userMapper.toAvailability(request, user);


        availability = availabilityRepo.save(availability);

        return userMapper.toAvailabilityPostResponse(availability);
    }

    public void deleteAvailability(String username, Long availabilityId) {
        availabilityRepo.deleteByIdAndUser_Username(availabilityId, username);
    }
}
