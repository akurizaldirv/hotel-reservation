package com.enigma.hotelreservation.model.response.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RoomIdNameResponse {
    private Integer id;
    private String roomType;
    private Integer roomNumber;
}
