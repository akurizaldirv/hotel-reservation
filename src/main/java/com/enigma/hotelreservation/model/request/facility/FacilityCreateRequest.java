package com.enigma.hotelreservation.model.request.facility;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class FacilityCreateRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;
}
