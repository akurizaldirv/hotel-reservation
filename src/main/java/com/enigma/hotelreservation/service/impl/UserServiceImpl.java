package com.enigma.hotelreservation.service.impl;

import com.enigma.hotelreservation.model.entity.AppUser;
import com.enigma.hotelreservation.model.entity.UserCredential;
import com.enigma.hotelreservation.repository.UserCredentialRepository;
import com.enigma.hotelreservation.service.UserService;
import com.enigma.hotelreservation.util.exception.DataNotFoundException;
import com.enigma.hotelreservation.util.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserCredentialRepository userCredentialRepository;
    @Override
    public AppUser loadUserByUserId(Integer id) {
        UserCredential userCredential = userCredentialRepository.getById(id);
        if (userCredential == null) throw new DataNotFoundException("User not found");

        return AuthMapper.mapToAppUser(userCredential);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws DataNotFoundException {
        UserCredential userCredential = userCredentialRepository.getByUsername(username);
        if (userCredential == null) throw new DataNotFoundException("User not found");

        return AuthMapper.mapToAppUser(userCredential);
    }
}
