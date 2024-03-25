package com.enigma.hotelreservation.util.mapper;

import com.enigma.hotelreservation.model.entity.Room;
import com.enigma.hotelreservation.model.response.room.RoomResponse;
import com.enigma.hotelreservation.model.response.roomtype.RoomTypeIdNameResponse;

public class RoomMapper {
    public static RoomResponse mapToRes(Room room, RoomTypeIdNameResponse roomTypeIdNameResponse, Long price) {
        return RoomResponse.builder()
                .id(room.getId())
                .price(price)
                .roomNumber(room.getRoomNumber())
                .roomType(roomTypeIdNameResponse)
                .build();
    }
}
