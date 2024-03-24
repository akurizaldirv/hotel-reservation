package com.enigma.hotelreservation.model.response.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class FacilityResponse {
    private Integer id;
    private String name;
}
