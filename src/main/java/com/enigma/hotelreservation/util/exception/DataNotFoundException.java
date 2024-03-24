package com.enigma.hotelreservation.util.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String message) {
        super(message);
    }
}
