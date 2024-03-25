package com.enigma.hotelreservation.model.response.room;


import com.enigma.hotelreservation.model.response.roomtype.RoomTypeIdNameResponse;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RoomResponse {
    private Integer id;
    private RoomTypeIdNameResponse roomType;
    private Integer roomNumber;
    private Long price;
}
