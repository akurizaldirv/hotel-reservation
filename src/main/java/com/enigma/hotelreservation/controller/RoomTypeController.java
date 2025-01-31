package com.enigma.hotelreservation.controller;

import com.enigma.hotelreservation.constant.AppPath;
import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.request.roomtype.RoomTypeCreateRequest;
import com.enigma.hotelreservation.model.request.roomtype.RoomTypeUpdateRequest;
import com.enigma.hotelreservation.model.response.CommonResponse;
import com.enigma.hotelreservation.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.ROOM_TYPE)
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .statusCode(HttpStatus.OK.value())
                                .message(ResponseMessage.GET_DATA_SUCCESS)
                                .data(roomTypeService.getAll())
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
                                .data(roomTypeService.getById(id))
                                .build()
                );
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@Validated @RequestBody RoomTypeCreateRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .statusCode(HttpStatus.OK.value())
                                .message(ResponseMessage.CREATE_DATA_SUCCESS)
                                .data(roomTypeService.create(request))
                                .build()
                );
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@Validated @RequestBody RoomTypeUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .statusCode(HttpStatus.OK.value())
                                .message(ResponseMessage.UPDATE_DATA_SUCCESS)
                                .data(roomTypeService.update(request))
                                .build()
                );
    }

    @DeleteMapping(AppPath.ID)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        roomTypeService.delete(id);
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
