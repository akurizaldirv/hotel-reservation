package com.enigma.hotelreservation.util.mapper;

import com.enigma.hotelreservation.model.entity.Payment;
import com.enigma.hotelreservation.model.response.payment.PaymentResponse;
import com.enigma.hotelreservation.model.response.reservation.ReservationResponse;

public class PaymentMapper {
    public static PaymentResponse mapToRes(Payment payment, ReservationResponse reservation) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .trxDate(payment.getTrxDate())
                .reservation(reservation)
                .build();
    }
}
