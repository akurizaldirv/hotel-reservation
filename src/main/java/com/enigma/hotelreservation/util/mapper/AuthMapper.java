package com.enigma.hotelreservation.util.mapper;

import com.enigma.hotelreservation.model.entity.AppUser;
import com.enigma.hotelreservation.model.entity.UserCredential;
import com.enigma.hotelreservation.model.response.auth.LoginResponse;
import com.enigma.hotelreservation.model.response.auth.RegisterResponse;

public class AuthMapper {
    public static AppUser mapToAppUser(UserCredential userCredential) {
        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getUsername())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getRole())
                .build();
    }

    public static RegisterResponse mapToRegisterRes(UserCredential userCredential) {
        return RegisterResponse.builder()
                .username(userCredential.getUsername())
                .role(userCredential.getRole().getRole().name())
                .build();
    }
}
