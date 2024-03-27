package com.enigma.hotelreservation.model.response.reservation;

import com.enigma.hotelreservation.model.response.customer.CustomerResponse;
import com.enigma.hotelreservation.model.response.room.RoomIdNameResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReservationResponse {
    private Integer id;
    private RoomIdNameResponse room;
    private CustomerResponse customer;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private Long price;
}
