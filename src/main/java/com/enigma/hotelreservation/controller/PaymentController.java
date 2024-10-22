package com.enigma.hotelreservation.controller;

import com.enigma.hotelreservation.constant.AppPath;
import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.response.CommonResponse;
import com.enigma.hotelreservation.service.PaymentService;
import com.enigma.hotelreservation.util.enums.EReservationStatus;
import com.enigma.hotelreservation.util.validation.EResvStatusValidator;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.PAYMENT)
public class PaymentController {

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
                                .data(paymentService.getAll(eStatus))
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
                                .data(paymentService.getById(id))
                                .build()
                );
    }

    @GetMapping(AppPath.ID + AppPath.DOWNLOAD)
    public void download(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        paymentService.downloadPayment(id, response);
    }

    @PutMapping(AppPath.ID + AppPath.APPROVE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> approve(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .statusCode(HttpStatus.OK.value())
                                .message(ResponseMessage.APPROVE_SUCCESS)
                                .data(paymentService.approve(id))
                                .build()
                );
    }

    @PutMapping(AppPath.ID + AppPath.REJECT)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> reject(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .statusCode(HttpStatus.OK.value())
                                .message(ResponseMessage.REJECT_SUCCESS)
                                .data(paymentService.reject(id))
                                .build()
                );
    }
}
