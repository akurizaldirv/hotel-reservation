package com.enigma.hotelreservation.service;

import com.enigma.hotelreservation.model.entity.RoomType;
import com.enigma.hotelreservation.model.request.roomtype.RoomTypeCreateRequest;
import com.enigma.hotelreservation.model.request.roomtype.RoomTypeUpdateRequest;
import com.enigma.hotelreservation.model.response.roomtype.RoomTypeIdNameResponse;
import com.enigma.hotelreservation.model.response.roomtype.RoomTypeResponse;

import java.util.List;

public interface RoomTypeService {
    List<RoomTypeResponse> getAll();
    RoomTypeResponse getById(Integer id);
    RoomTypeIdNameResponse getIdNameById(Integer id);
    RoomType getRoomTypeById(Integer id);
    RoomType getActiveRoomTypeById(Integer id);
    RoomType getLastRoomType();
    RoomTypeResponse create(RoomTypeCreateRequest request);
    RoomTypeResponse update(RoomTypeUpdateRequest request);
    void delete(Integer id);
    Boolean isExist(Integer id);
}
