package com.enigma.hotelreservation.service;

import com.enigma.hotelreservation.model.entity.Reservation;
import com.enigma.hotelreservation.model.request.reservation.ReservationCreateRequest;
import com.enigma.hotelreservation.model.response.reservation.ReservationResponse;
import com.enigma.hotelreservation.util.enums.EReservationStatus;

import java.util.List;

public interface ReservationService {
    Reservation getReservationById(Integer id);
    ReservationResponse getById(Integer id);
    ReservationResponse cancelById(Integer id);
    ReservationResponse setInvalidPaymentById(Integer id);
    ReservationResponse setPaymentSuccessById(Integer id);
    ReservationResponse setPaymentCheckingById(Integer id);
    Reservation getLastReservation();
    List<ReservationResponse> getAll(EReservationStatus status);
    ReservationResponse create(ReservationCreateRequest request);
    Boolean isExist(Integer id);
}
