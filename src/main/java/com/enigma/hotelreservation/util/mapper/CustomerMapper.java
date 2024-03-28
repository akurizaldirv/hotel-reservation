package com.enigma.hotelreservation.util.mapper;

import com.enigma.hotelreservation.model.entity.Customer;
import com.enigma.hotelreservation.model.response.customer.CustomerResponse;

public class CustomerMapper {
    public static CustomerResponse mapToRes(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .identityNumber(customer.getIdentityNumber())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
}
