package com.enigma.hotelreservation.service.impl;

import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.entity.Payment;
import com.enigma.hotelreservation.model.entity.Reservation;
import com.enigma.hotelreservation.model.response.payment.PaymentResponse;
import com.enigma.hotelreservation.model.response.reservation.ReservationResponse;
import com.enigma.hotelreservation.repository.PaymentRepository;
import com.enigma.hotelreservation.service.PaymentService;
import com.enigma.hotelreservation.service.ReservationService;
import com.enigma.hotelreservation.util.FileUtil;
import com.enigma.hotelreservation.util.enums.EReservationStatus;
import com.enigma.hotelreservation.util.exception.DataNotFoundException;
import com.enigma.hotelreservation.util.exception.QueryException;
import com.enigma.hotelreservation.util.mapper.PaymentMapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ReservationService reservationService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public PaymentResponse pay(Integer resvId, MultipartFile file) throws IOException {
        if (file.isEmpty()) throw new ValidationException(ResponseMessage.EMPTY_FILE);
        Reservation reservation = reservationService.getReservationById(resvId);
        if (reservation.getStatus() != EReservationStatus.PAYMENT) throw new ValidationException(ResponseMessage.INVALID_PAY_RSVP);
        if (!LocalDate.now().isBefore(reservation.getCheckinDate())) throw new ValidationException(ResponseMessage.OVERDUE_PAYMENT);

        String extension = file.getContentType().split("/")[1];
        String fileName = FileUtil.generateUniqueFilename(resvId) + "." + extension;
        byte[] bytes = file.getBytes();
        File newFile = new File("payment/" + fileName);
        FileOutputStream fos = new FileOutputStream(newFile);
        fos.write(bytes);
        fos.close();

        int rowsChange = paymentRepository.insert(fileName, LocalDateTime.now(), resvId);
        if (rowsChange == 0) throw new QueryException(ResponseMessage.UPDATE_DATA_FAILED);
        ReservationResponse reservationResponse = reservationService.setPaymentCheckingById(resvId);

        Payment payment = paymentRepository.getLastPayment(resvId);
        return PaymentMapper.mapToRes(payment, reservationResponse);
    }

    @Override
    public PaymentResponse getById(Integer id) {
        Payment payment = this.getPaymentById(id);
        ReservationResponse reservation = reservationService.getById(payment.getReservation().getId());
        return PaymentMapper.mapToRes(payment, reservation);
    }

    @Override
    public Payment getPaymentById(Integer id) {
        Payment payment = paymentRepository.getPaymentById(id);
        if (payment == null) throw new DataNotFoundException(ResponseMessage.PAYMENT_NOT_FOUND);
        return payment;
    }

    @Override
    public List<PaymentResponse> getAll(EReservationStatus status) {
        List<Payment> payments;
        if (status != null) {
            payments = this.getAllByStatus(status);
        } else {
            payments = paymentRepository.getAll();
        }

        return payments.stream().map(payment -> {
            ReservationResponse reservation = reservationService.getById(payment.getReservation().getId());
            return PaymentMapper.mapToRes(payment, reservation);
        }) .toList();
    }

    private List<Payment> getAllByStatus(EReservationStatus status) {
        return paymentRepository.getAllByStatus(status.name());
    }

    @Override
    public PaymentResponse approve(Integer id) {
        Payment payment = this.getPaymentById(id);
        Reservation reservation = reservationService.getReservationById(payment.getReservation().getId());
        if (reservation.getStatus() != EReservationStatus.CHECKING) throw new ValidationException(ResponseMessage.INVALID_APPROVE_PAYMENT);

        ReservationResponse reservationResponse = reservationService.setPaymentSuccessById(payment.getReservation().getId());
        return PaymentMapper.mapToRes(payment, reservationResponse);
    }

    @Override
    public PaymentResponse reject(Integer id) {
        Payment payment = this.getPaymentById(id);
        Reservation reservation = reservationService.getReservationById(payment.getReservation().getId());
        if (reservation.getStatus() != EReservationStatus.CHECKING) throw new ValidationException(ResponseMessage.INVALID_REJECT_PAYMENT);

        ReservationResponse reservationResponse = reservationService.setInvalidPaymentById(payment.getReservation().getId());
        return PaymentMapper.mapToRes(payment, reservationResponse);
    }

    @Override
    public void downloadPayment(Integer id, HttpServletResponse response) throws IOException {

    }
}
