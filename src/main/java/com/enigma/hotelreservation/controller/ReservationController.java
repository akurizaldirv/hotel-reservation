package com.enigma.hotelreservation.controller;

import com.enigma.hotelreservation.constant.AppPath;
import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.request.reservation.ReservationCreateRequest;
import com.enigma.hotelreservation.model.request.room.RoomCreateRequest;
import com.enigma.hotelreservation.model.request.room.RoomUpdateRequest;
import com.enigma.hotelreservation.model.response.CommonResponse;
import com.enigma.hotelreservation.service.PaymentService;
import com.enigma.hotelreservation.service.ReservationService;
import com.enigma.hotelreservation.util.enums.EReservationStatus;
import com.enigma.hotelreservation.util.validation.EResvStatusValidator;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.RESERVATION)
@SecurityRequirement(name = "Bearer Authentication")
public class ReservationController {
    private final ReservationService reservationService;
    private final PaymentService paymentService;
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "null") String status) {
        EReservationStatus eStatus = EResvStatusValidator.validate(status);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .statusCode(HttpStatus.OK.value())
                                .message(ResponseMessage.GET_DATA_SUCCESS)
                                .data(reservationService.getAll(eStatus))
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
                                .data(reservationService.getById(id))
                                .build()
                );
    }

    @PutMapping(AppPath.ID + AppPath.CANCEL)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    public ResponseEntity<?> cancel(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .statusCode(HttpStatus.OK.value())
                                .message(ResponseMessage.CANCEL_SUCCESS)
                                .data(reservationService.cancelById(id))
                                .build()
                );
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    public ResponseEntity<?> create(@Validated @RequestBody ReservationCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        CommonResponse.builder()
                                .statusCode(HttpStatus.CREATED.value())
                                .message(ResponseMessage.CREATE_DATA_SUCCESS)
                                .data(reservationService.create(request))
                                .build()
                );
    }

    @PutMapping(AppPath.ID + AppPath.PAY)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    public ResponseEntity<?> pay(@PathVariable Integer id, @RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        CommonResponse.builder()
                                .statusCode(HttpStatus.CREATED.value())
                                .message(ResponseMessage.PAYMENT_SUCCESS)
                                .data(paymentService.pay(id, file))
                                .build()
                );
    }
}
