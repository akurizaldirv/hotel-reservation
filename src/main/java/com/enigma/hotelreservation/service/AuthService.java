package com.enigma.hotelreservation.service;

import com.enigma.hotelreservation.model.entity.UserCredential;
import com.enigma.hotelreservation.model.request.auth.LoginRequest;
import com.enigma.hotelreservation.model.request.auth.RegisterAdminRequest;
import com.enigma.hotelreservation.model.request.auth.RegisterCustomerRequest;
import com.enigma.hotelreservation.model.response.auth.LoginResponse;
import com.enigma.hotelreservation.model.response.auth.RegisterResponse;

public interface AuthService {
    UserCredential getLastUserCredential();
    RegisterResponse registerAdmin(RegisterAdminRequest request);
    RegisterResponse registerCustomer(RegisterCustomerRequest request);
    LoginResponse login(LoginRequest request);
}
