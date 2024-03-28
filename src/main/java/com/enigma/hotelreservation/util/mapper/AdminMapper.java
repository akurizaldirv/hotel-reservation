package com.enigma.hotelreservation.util.mapper;

import com.enigma.hotelreservation.model.entity.Admin;
import com.enigma.hotelreservation.model.entity.Customer;
import com.enigma.hotelreservation.model.response.admin.AdminResponse;
import com.enigma.hotelreservation.model.response.customer.CustomerResponse;

public class AdminMapper {
    public static AdminResponse mapToRes(Admin admin) {
        return AdminResponse.builder()
                .id(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail())
                .address(admin.getAddress())
                .phoneNumber(admin.getPhoneNumber())
                .build();
    }
}
