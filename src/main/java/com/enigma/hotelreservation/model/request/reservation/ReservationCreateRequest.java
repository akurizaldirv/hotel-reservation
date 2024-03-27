package com.enigma.hotelreservation.model.request.reservation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReservationCreateRequest {
    @NotNull(message = "Room ID cannot be null")
    private Integer roomId;
    @NotNull(message = "Room ID cannot be null")
    private Integer customerId;
    @NotBlank(message = "Checkin Date cannot be blank")
    private String checkinDate;
    @NotBlank(message = "Checkout Date cannot be blank")
    private String checkoutDate;
}
