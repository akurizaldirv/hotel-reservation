package com.enigma.hotelreservation.model.request.roomtype;

import com.enigma.hotelreservation.model.entity.BedType;
import com.enigma.hotelreservation.model.entity.RoomFacility;
import com.enigma.hotelreservation.util.enums.EStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RoomTypeCreateRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Bed Type cannot be blank")
    private String bedType;

    @NotNull(message = "Bed Count cannot be null")
    @Min(value = 1, message = "Bed Count must be greater or equal than 1")
    private Integer bedCount;

    @NotEmpty(message = "Facility ID cannot be empty")
    private List<@NotNull(message = "Facility ID cannot be null") Integer> facilitiesId;
}
