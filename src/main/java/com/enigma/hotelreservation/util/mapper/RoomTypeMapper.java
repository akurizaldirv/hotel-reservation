package com.enigma.hotelreservation.util.mapper;

import com.enigma.hotelreservation.model.entity.RoomType;
import com.enigma.hotelreservation.model.response.roomtype.RoomTypeIdNameResponse;
import com.enigma.hotelreservation.model.response.roomtype.RoomTypeResponse;

import java.util.List;

public class RoomTypeMapper {
    public static RoomTypeResponse mapToRes(RoomType roomType, List<String> facilities) {
        return RoomTypeResponse.builder()
                .id(roomType.getId())
                .name(roomType.getName())
                .bedType(roomType.getBedType().getName().name())
                .bedCount(roomType.getBedCount())
                .facilities(facilities)
                .build();
    }

    public static RoomTypeIdNameResponse mapToIdNameRes(RoomType roomType) {
        return RoomTypeIdNameResponse.builder()
                .id(roomType.getId())
                .name(roomType.getName())
                .build();
    }
}
