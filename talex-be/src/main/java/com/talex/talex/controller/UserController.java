package com.talex.talex.controller;

import com.talex.talex.dto.req.AvailabilityPostRequest;
import com.talex.talex.dto.req.MePutRequest;
import com.talex.talex.dto.req.OfferedSkillPostRequest;
import com.talex.talex.dto.req.WantedSkillPostRequest;
import com.talex.talex.dto.res.AvailabilityPostResponse;
import com.talex.talex.dto.res.MeResponse;
import com.talex.talex.dto.res.OfferedSkillPostResponse;
import com.talex.talex.dto.res.WantedSkillPostResponse;
import com.talex.talex.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    // service
    public final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<MeResponse> getMe(Principal principal) {
        String username = principal.getName();
        return ResponseEntity.status(HttpStatus.OK).body(userService.getMe(username));
    }

    @PutMapping("/me")
    public ResponseEntity<MeResponse> putMe(Principal principal, @Valid @RequestBody MePutRequest request){
        String username = principal.getName();
        return ResponseEntity.status(HttpStatus.OK).body(userService.putMe(username, request));
    }

    @PutMapping("/profilepicture")
    public ResponseEntity<Void> putProfilePicture(Principal principal, @RequestParam String url) {
        String username = principal.getName();
        userService.updateProfilePicture(username, url);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/skill/offered")
    public ResponseEntity<OfferedSkillPostResponse> addOfferedSkill(@Valid @RequestBody OfferedSkillPostRequest request, Principal principal) {
        String username = principal.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addOfferedSkill(request, username));
    }

    @DeleteMapping("/skill/offered/{id}")
    public ResponseEntity<Void> DeleteOfferedSkill(@PathVariable("id") Long skillId, Principal principal) {
        String username = principal.getName();
        userService.deleteOfferedSkill(username, skillId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/skill/wanted")
    public ResponseEntity<WantedSkillPostResponse> addOfferedSkill(@Valid @RequestBody WantedSkillPostRequest request, Principal principal) {
        String username = principal.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addWantedSkill(request, username));
    }

    @DeleteMapping("/skill/wanted/{id}")
    public ResponseEntity<Void> DeleteWantedSkill(@PathVariable("id") Long skillId, Principal principal) {
        String username = principal.getName();
        userService.deleteWantedSkill(username, skillId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/availability")
    public ResponseEntity<AvailabilityPostResponse> addAvailability(@Valid @RequestBody AvailabilityPostRequest request, Principal principal) {
        String username = principal.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addAvailability(request, username));
    }

    @DeleteMapping("/availability/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable("id") Long availabilityId, Principal principal) {
        String username = principal.getName();
        userService.deleteAvailability(username, availabilityId);
        return ResponseEntity.noContent().build();
    }



}
