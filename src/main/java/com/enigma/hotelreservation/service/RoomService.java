package com.enigma.hotelreservation.service;

import com.enigma.hotelreservation.model.entity.Room;
import com.enigma.hotelreservation.model.request.room.RoomCreateRequest;
import com.enigma.hotelreservation.model.request.room.RoomUpdateRequest;
import com.enigma.hotelreservation.model.response.room.RoomResponse;

import java.util.List;

public interface RoomService {
    List<RoomResponse> getAll();
    RoomResponse getById(Integer id);
    Room getRoomById(Integer id);
    Room getActiveRoomById(Integer id);
    Room getLastRoom();
    RoomResponse create(RoomCreateRequest request);
    RoomResponse update(RoomUpdateRequest request);
    void delete(Integer id);
    Boolean isExist(Integer id);
}
