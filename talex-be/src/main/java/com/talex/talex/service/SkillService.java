package com.talex.talex.service;


import com.talex.talex.entity.Skill;
import com.talex.talex.entity.UserSkillOffered;
import com.talex.talex.repo.SkillRepo;
import com.talex.talex.repo.UserSkillOfferedRepo;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class SkillService {

    // repo

    public final SkillRepo skillRepo;
    public final UserSkillOfferedRepo userSkillOfferedRepo;


    public Skill getSkill(Long aLong) {
        return skillRepo.findById(aLong)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Skill not found"
                ));
    }



    public UserSkillOffered getSkillOffered(@NotNull(message = "Sender skill id is required") Long aLong) {
        return userSkillOfferedRepo.findById(aLong)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Skill not found"
                ));
    }
}
