package com.enigma.hotelreservation.controller;

import com.enigma.hotelreservation.constant.AppPath;
import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.request.user.AdminUpdateRequest;
import com.enigma.hotelreservation.model.response.CommonResponse;
import com.enigma.hotelreservation.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(AppPath.BASE_ADMIN)
public class AdminController {
    private final AdminService adminService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .message(ResponseMessage.GET_DATA_SUCCESS)
                                .statusCode(HttpStatus.OK.value())
                                .data(adminService.getAll())
                                .build()
                );
    }

    @GetMapping(AppPath.ID)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .message(ResponseMessage.GET_DATA_SUCCESS)
                                .statusCode(HttpStatus.OK.value())
                                .data(adminService.getById(id))
                                .build()
                );
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@RequestBody AdminUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .message(ResponseMessage.GET_DATA_SUCCESS)
                                .statusCode(HttpStatus.OK.value())
                                .data(adminService.update(request))
                                .build()
                );
    }
}
