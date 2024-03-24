package com.enigma.hotelreservation.util.mapper;

import com.enigma.hotelreservation.model.entity.Facility;
import com.enigma.hotelreservation.model.response.response.FacilityResponse;

public class FacilityMapper {
    public static FacilityResponse mapToRes(Facility facility) {
        return FacilityResponse.builder()
                .id(facility.getId())
                .name(facility.getName())
                .build();
    }
}
