package com.enigma.hotelreservation.controller;

import com.enigma.hotelreservation.constant.AppPath;
import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.request.room.RoomCreateRequest;
import com.enigma.hotelreservation.model.request.room.RoomUpdateRequest;
import com.enigma.hotelreservation.model.response.CommonResponse;
import com.enigma.hotelreservation.service.RoomService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.ROOM)
@SecurityRequirement(name = "Bearer Authentication")
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .statusCode(HttpStatus.OK.value())
                                .message(ResponseMessage.GET_DATA_SUCCESS)
                                .data(roomService.getAll())
                                .build()
                );
    }

    @GetMapping(AppPath.ID)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .statusCode(HttpStatus.OK.value())
                                .message(ResponseMessage.GET_DATA_SUCCESS)
                                .data(roomService.getById(id))
                                .build()
                );
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@Validated @RequestBody RoomCreateRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .statusCode(HttpStatus.OK.value())
                                .message(ResponseMessage.CREATE_DATA_SUCCESS)
                                .data(roomService.create(request))
                                .build()
                );
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@Validated @RequestBody RoomUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .statusCode(HttpStatus.OK.value())
                                .message(ResponseMessage.UPDATE_DATA_SUCCESS)
                                .data(roomService.update(request))
                                .build()
                );
    }

    @DeleteMapping(AppPath.ID)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        roomService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .statusCode(HttpStatus.OK.value())
                                .message(ResponseMessage.DELETE_DATA_SUCCESS)
                                .data(new ArrayList<>())
                                .build()
                );
    }
}
