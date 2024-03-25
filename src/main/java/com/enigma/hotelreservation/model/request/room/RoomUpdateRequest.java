package com.enigma.hotelreservation.model.request.room;


import jakarta.validation.constraints.Min;
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
public class RoomUpdateRequest {
    @NotNull(message = "Room ID cannot be null")
    private Integer id;
    @NotNull(message = "Room Type ID cannot be null")
    private Integer roomTypeId;
    @NotNull(message = "Room Number cannot be null")
    private Integer roomNumber;
    @NotNull(message = "Price cannot be null")
    @Min(value = 1, message = "Price must be greater or equal than 1")
    private Long price;
}
