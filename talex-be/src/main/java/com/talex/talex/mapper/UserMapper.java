package com.talex.talex.mapper;


import com.talex.talex.dto.req.SignupRequest;
import com.talex.talex.dto.res.LoginResponse;
import com.talex.talex.dto.res.SignupResponse;
import com.talex.talex.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    User toUser(SignupRequest request);

    SignupResponse toSignupResponse(User user);

    @Mapping(target = "token", source = "token")
    LoginResponse toLoginResponse(User user, String token);

}
