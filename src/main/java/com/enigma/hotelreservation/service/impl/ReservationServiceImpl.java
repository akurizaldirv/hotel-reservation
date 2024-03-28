package com.enigma.hotelreservation.service.impl;

import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.entity.Reservation;
import com.enigma.hotelreservation.model.entity.RoomPrice;
import com.enigma.hotelreservation.model.request.reservation.ReservationCreateRequest;
import com.enigma.hotelreservation.model.response.customer.CustomerResponse;
import com.enigma.hotelreservation.model.response.reservation.ReservationResponse;
import com.enigma.hotelreservation.model.response.room.RoomIdNameResponse;
import com.enigma.hotelreservation.model.response.room.RoomResponse;
import com.enigma.hotelreservation.repository.ReservationRepository;
import com.enigma.hotelreservation.service.CustomerService;
import com.enigma.hotelreservation.service.ReservationService;
import com.enigma.hotelreservation.service.RoomService;
import com.enigma.hotelreservation.util.enums.EReservationStatus;
import com.enigma.hotelreservation.util.exception.DataNotFoundException;
import com.enigma.hotelreservation.util.validation.LocalDateValidator;
import com.enigma.hotelreservation.util.mapper.ReservationMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerService customerService;
    private final RoomService roomService;

    @Override
    public Reservation getReservationById(Integer id) {
        Reservation reservation = reservationRepository.getById(id);
        if (reservation == null) throw new DataNotFoundException(ResponseMessage.RESERVATION_NOT_FOUND);
        return reservation;
    }

    @Override
    public ReservationResponse getById(Integer id) {
        Reservation reservation = this.getReservationById(id);
        return this.getReservationResponse(reservation);
    }

    @Override
    public ReservationResponse cancelById(Integer id) {
        if (!this.isExist(id)) throw new DataNotFoundException(ResponseMessage.RESERVATION_NOT_FOUND);
        reservationRepository.updateStatus(EReservationStatus.CANCELLED.name(), id);
        return this.getReservationResponse(this.getReservationById(id));
    }

    @Override
    public ReservationResponse setInvalidPaymentById(Integer id) {
        if (!this.isExist(id)) throw new DataNotFoundException(ResponseMessage.RESERVATION_NOT_FOUND);
        reservationRepository.updateStatus(EReservationStatus.PAYMENT_INVALID.name(), id);
        return this.getReservationResponse(this.getReservationById(id));
    }

    @Override
    public ReservationResponse setPaymentSuccessById(Integer id) {
        if (!this.isExist(id)) throw new DataNotFoundException(ResponseMessage.RESERVATION_NOT_FOUND);
        reservationRepository.updateStatus(EReservationStatus.SUCCESS.name(), id);
        return this.getReservationResponse(this.getReservationById(id));
    }

    @Override
    public ReservationResponse setPaymentCheckingById(Integer id) {
        if (!this.isExist(id)) throw new DataNotFoundException(ResponseMessage.RESERVATION_NOT_FOUND);
        reservationRepository.updateStatus(EReservationStatus.CHECKING.name(), id);
        return this.getReservationResponse(this.getReservationById(id));
    }

    @Override
    public Reservation getLastReservation() {
        return reservationRepository.getLastReservation();
    }

    @Override
    public List<ReservationResponse> getAll(EReservationStatus status) {
        List<Reservation> reservations;
        if (status == null) {
            reservations = reservationRepository.getAll();
        } else {
            reservations = this.getAllByStatus(status);
        }

        return reservations.stream().map(this::getReservationResponse).toList();
    }

    private List<Reservation> getAllByStatus(EReservationStatus status) {
        return reservationRepository.getAllByStatus(status.name());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ReservationResponse create(ReservationCreateRequest request) {
        RoomPrice roomPrice = roomService.getActiveRoomPriceByRoomId(request.getRoomId());
        
        LocalDate dateStart = LocalDateValidator.mapToLocalDate(request.getCheckinDate());
        LocalDate dateEnd = LocalDateValidator.mapToLocalDate(request.getCheckoutDate());

        if (LocalDate.now().isAfter(dateStart) || dateStart.isAfter(dateEnd)) {
            throw new ValidationException(ResponseMessage.INVALID_RSVP_DATE);
        }

        dateStart.datesUntil(dateEnd.plusDays(1)).forEach(date -> {
            if (!this.isAvailable(date, request.getRoomId())) throw new ValidationException("Room ID: " + request.getRoomId() +
                    " for date: " + date + " is Unavailable");
        });

        if (!LocalDateValidator.isResvDateValid(dateStart, dateEnd)) throw new ValidationException(ResponseMessage.INVALID_RSVP_DATE);

        reservationRepository.insert(
                dateEnd,
                dateStart,
                request.getCustomerId(),
                roomPrice.getId()
        );
        return this.getReservationResponse(reservationRepository.getLastReservation());
    }

    private ReservationResponse getReservationResponse(Reservation reservation) {
        CustomerResponse customer = customerService.getById(reservation.getCustomer().getId());
        RoomResponse room = roomService.getById(reservation.getRoomPrice().getRoom().getId());
        RoomIdNameResponse roomIdNameResponse = RoomIdNameResponse.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .roomType(room.getRoomType().getName())
                .build();
        Integer duration = reservation.getCheckoutDate().compareTo(reservation.getCheckinDate());
        if (duration == 0) duration = 1;
        Long totalPrice = duration * reservation.getRoomPrice().getPrice();

        return ReservationMapper.mapToRes(reservation, customer, roomIdNameResponse, totalPrice);
    }

    @Override
    public Boolean isExist(Integer id) {
        Reservation reservation = this.getReservationById(id);
        return reservation != null;
    }

    private Boolean isAvailable(LocalDate date, Integer roomId) {
        Integer count = reservationRepository.getCountByDateBetween(date, roomId);
        return count == 0;
    }
}
