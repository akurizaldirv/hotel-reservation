package com.enigma.hotelreservation.service.impl;

import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.entity.*;
import com.enigma.hotelreservation.model.request.auth.LoginRequest;
import com.enigma.hotelreservation.model.request.auth.RegisterAdminRequest;
import com.enigma.hotelreservation.model.request.auth.RegisterCustomerRequest;
import com.enigma.hotelreservation.model.response.auth.LoginResponse;
import com.enigma.hotelreservation.model.response.auth.RegisterResponse;
import com.enigma.hotelreservation.repository.UserCredentialRepository;
import com.enigma.hotelreservation.service.*;
import com.enigma.hotelreservation.util.enums.ERole;
import com.enigma.hotelreservation.util.exception.QueryException;
import com.enigma.hotelreservation.util.mapper.AuthMapper;
import com.enigma.hotelreservation.util.security.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AdminService adminService;
    private final CustomerService customerService;
    private final UserCredentialRepository userCredentialRepository;
    private final RoleService roleService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserCredential getLastUserCredential() {
        return userCredentialRepository.getLastUserCredential();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse registerAdmin(RegisterAdminRequest request) {
        Role role = roleService.getOrSave(ERole.ROLE_ADMIN);
        int rowsChange = userCredentialRepository.insertUserCredential(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                role.getId()
        );
        if (rowsChange == 0) throw new QueryException(ResponseMessage.CREATE_DATA_FAILED);
        UserCredential userCredential = this.getLastUserCredential();
        Admin admin = adminService.create(request, userCredential);

        return AuthMapper.mapToRegisterRes(userCredential);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse registerCustomer(RegisterCustomerRequest request) {
        Role role = roleService.getOrSave(ERole.ROLE_CUSTOMER);
        int rowsChange = userCredentialRepository.insertUserCredential(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                role.getId()
        );
        if (rowsChange == 0) throw new QueryException(ResponseMessage.CREATE_DATA_FAILED);
        UserCredential userCredential = this.getLastUserCredential();
        Customer customer = customerService.create(request, userCredential);

        return AuthMapper.mapToRegisterRes(userCredential);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername().toLowerCase(),
                        request.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRole().name())
                .build();
    }
}
