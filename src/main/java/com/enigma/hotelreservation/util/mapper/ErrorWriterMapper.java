package com.enigma.hotelreservation.util.mapper;

import com.enigma.hotelreservation.model.response.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;

public class ErrorWriterMapper {
    public static String mapToString(String message, Integer statusCode) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ErrorResponse errorResponse = new ErrorResponse(statusCode, message,new Date());
        return objectMapper.writeValueAsString(errorResponse);
    }
}
