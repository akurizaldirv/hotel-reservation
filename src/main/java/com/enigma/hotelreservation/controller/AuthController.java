package com.enigma.hotelreservation.controller;

import com.enigma.hotelreservation.constant.AppPath;
import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.request.auth.LoginRequest;
import com.enigma.hotelreservation.model.request.auth.RegisterAdminRequest;
import com.enigma.hotelreservation.model.request.auth.RegisterCustomerRequest;
import com.enigma.hotelreservation.model.response.CommonResponse;
import com.enigma.hotelreservation.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.AUTH)
public class AuthController {

    private final AuthService authService;

    @PostMapping(AppPath.REGISTER + AppPath.ADMIN)
    public ResponseEntity<?> registerAdmin(@Validated @RequestBody RegisterAdminRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .statusCode(HttpStatus.OK.value())
                                .message(ResponseMessage.REGISTER_SUCCESS)
                                .data(authService.registerAdmin(request))
                                .build()
                );
    }

    @PostMapping(AppPath.REGISTER + AppPath.CUSTOMER)
    public ResponseEntity<?> registerCustomer(@Validated @RequestBody RegisterCustomerRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .statusCode(HttpStatus.OK.value())
                                .message(ResponseMessage.REGISTER_SUCCESS)
                                .data(authService.registerCustomer(request))
                                .build()
                );
    }

    @PostMapping(AppPath.LOGIN)
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .statusCode(HttpStatus.OK.value())
                                .message(ResponseMessage.LOGIN_SUCCESS)
                                .data(authService.login(request))
                                .build()
                );
    }


}
