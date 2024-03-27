package com.enigma.hotelreservation.model.response.payment;

import com.enigma.hotelreservation.model.response.reservation.ReservationResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PaymentResponse {
    private Integer id;
    private LocalDateTime trxDate;
    private ReservationResponse reservation;
}
