package com.enigma.hotelreservation.util.validation;

import jakarta.validation.ValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateValidator {
    public static LocalDate mapToLocalDate(String date) {
        String[] dateFormats = {"yyyy-MM-dd", "yyyy-M-d", "yyyy-MM-d", "yyyy-M-dd"};

        return parseDate(date, dateFormats);
    }

    public static Boolean isResvDateValid(LocalDate dateStart, LocalDate dateEnd) {
        return !dateStart.isAfter(dateEnd);
    }

    private static LocalDate parseDate(String dateStr, String[] dateFormats) {
        for (String format : dateFormats) {
            try {
                return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(format));
            } catch (Exception e) {
                continue;
            }
        }
        throw new ValidationException("Invalid Date Format: " + dateStr);
    }
}
