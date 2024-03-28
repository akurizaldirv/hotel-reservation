package com.enigma.hotelreservation.controller;


import com.enigma.hotelreservation.constant.AppPath;
import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.request.user.AdminUpdateRequest;
import com.enigma.hotelreservation.model.request.user.CustomerUpdateRequest;
import com.enigma.hotelreservation.model.response.CommonResponse;
import com.enigma.hotelreservation.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.BASE_CUSTOMER)
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .message(ResponseMessage.GET_DATA_SUCCESS)
                                .statusCode(HttpStatus.OK.value())
                                .data(customerService.getAll())
                                .build()
                );
    }

    @GetMapping(AppPath.ID)
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .message(ResponseMessage.GET_DATA_SUCCESS)
                                .statusCode(HttpStatus.OK.value())
                                .data(customerService.getById(id))
                                .build()
                );
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CustomerUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .message(ResponseMessage.GET_DATA_SUCCESS)
                                .statusCode(HttpStatus.OK.value())
                                .data(customerService.update(request))
                                .build()
                );
    }


}
