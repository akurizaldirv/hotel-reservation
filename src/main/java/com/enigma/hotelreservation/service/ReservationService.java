package com.enigma.hotelreservation.service;

import com.enigma.hotelreservation.model.entity.Reservation;
import com.enigma.hotelreservation.model.request.reservation.ReservationCreateRequest;
import com.enigma.hotelreservation.model.response.reservation.ReservationResponse;

import java.util.List;

public interface ReservationService {
    Reservation getReservationById(Integer id);
    ReservationResponse getById(Integer id);
    ReservationResponse cancelById(Integer id);
    Reservation getLastReservation();
    List<ReservationResponse> getAll();
    ReservationResponse create(ReservationCreateRequest request);
    Boolean isExist(Integer id);
}
