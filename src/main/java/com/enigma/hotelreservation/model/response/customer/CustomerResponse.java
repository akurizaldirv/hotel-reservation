package com.enigma.hotelreservation.model.response.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CustomerResponse {
    private Integer id;
    private String identityNumber;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
}
