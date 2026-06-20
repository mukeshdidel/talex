package com.talex.talex.service;


import com.talex.talex.dto.req.LoginRequest;
import com.talex.talex.dto.req.SignupRequest;
import com.talex.talex.dto.res.LoginResponse;
import com.talex.talex.dto.res.SignupResponse;

import com.talex.talex.entity.Role;
import com.talex.talex.entity.User;

import com.talex.talex.mapper.UserMapper;

import com.talex.talex.repo.UserRepo;

import com.talex.talex.security.jwt.JwtService;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthService {


    // repo
    private final UserRepo userRepo;

    // service
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    // mapper
    private final UserMapper userMapper;

    public SignupResponse signup(SignupRequest request) {

        if(userRepo.existsByUsername(request.username())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "username already exists."
            );
        }
        if(userRepo.existsByEmail(request.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "user with email already exists."
            );
        }

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);

        user = userRepo.save(user);


        // todo: email verification -----


        return userMapper.toSignupResponse(user);

    }

    public LoginResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.usernameOrEmail(), request.password()));


        String login = authentication.getName();

        User user = userRepo.findByUsernameOrEmail(login, login)
                .orElseThrow(()->
                        new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "User not found."
                        )
                );

        String token = jwtService.generateToken(user);

        return userMapper.toLoginResponse(user, token);

        } catch (BadCredentialsException ex) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "username/email or password is wrong"
            );
        }
    }

}
