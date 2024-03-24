package com.enigma.hotelreservation.model.request.facility;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class FacilityUpdateRequest {
    @NotNull(message = "ID cannot be null")
    private Integer id;

    @NotBlank(message = "Name cannot be blank")
    private String name;
}
