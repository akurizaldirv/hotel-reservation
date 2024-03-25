package com.enigma.hotelreservation.service;

import com.enigma.hotelreservation.model.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(Integer id);
}
