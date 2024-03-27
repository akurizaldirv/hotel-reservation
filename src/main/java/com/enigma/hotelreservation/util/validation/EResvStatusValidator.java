package com.enigma.hotelreservation.util.validation;

import com.enigma.hotelreservation.util.enums.EReservationStatus;

public class EResvStatusValidator {
    public static EReservationStatus validate(String str) {
        return switch (str.toUpperCase()) {
            case "SUCCESS" -> EReservationStatus.SUCCESS;
            case "CANCELLED" -> EReservationStatus.CANCELLED;
            case "CHECKING" -> EReservationStatus.CHECKING;
            case "PAYMENT" -> EReservationStatus.PAYMENT;
            case "PAYMENT_INVALID" -> EReservationStatus.PAYMENT_INVALID;
            default -> null;
        };
    }
}
