package com.enigma.hotelreservation.model.response.roomtype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RoomTypeResponse {
    private Integer id;
    private String name;
    private String bedType;
    private Integer bedCount;
    private List<String> facilities;
}
