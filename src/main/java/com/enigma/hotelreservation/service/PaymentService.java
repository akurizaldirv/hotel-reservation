package com.enigma.hotelreservation.service;

import com.enigma.hotelreservation.model.entity.Payment;
import com.enigma.hotelreservation.model.response.payment.PaymentResponse;
import com.enigma.hotelreservation.util.enums.EReservationStatus;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PaymentService {
    PaymentResponse pay(Integer resvId, MultipartFile file) throws IOException;
    PaymentResponse getById(Integer id);
    Payment getPaymentById(Integer id);
    List<PaymentResponse> getAll(EReservationStatus status);
    PaymentResponse approve(Integer id);
    PaymentResponse reject(Integer id);
    void downloadPayment(Integer id, HttpServletResponse response) throws IOException;
}
