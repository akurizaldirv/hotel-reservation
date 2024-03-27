package com.enigma.hotelreservation.util.mapper;

import com.enigma.hotelreservation.model.entity.Reservation;
import com.enigma.hotelreservation.model.response.customer.CustomerResponse;
import com.enigma.hotelreservation.model.response.reservation.ReservationResponse;
import com.enigma.hotelreservation.model.response.room.RoomIdNameResponse;

public class ReservationMapper {
    public static ReservationResponse mapToRes(Reservation reservation, CustomerResponse customer, RoomIdNameResponse room, Long totalPrice) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .price(totalPrice)
                .checkinDate(reservation.getCheckinDate())
                .checkoutDate(reservation.getCheckoutDate())
                .customer(customer)
                .room(room)
                .status(reservation.getStatus().name())
                .build();
    }
}
