package com.enigma.hotelreservation.model.response.roomtype;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RoomTypeIdNameResponse {
    private Integer id;
    private String name;
}
