package com.enigma.hotelreservation.service;

import com.enigma.hotelreservation.model.entity.RoomFacility;

import java.util.List;

public interface RoomFacilityService {
    List<RoomFacility> getAllByRoomTypeId(Integer id);
    RoomFacility insertRoomFacility(Integer roomTypeId, Integer facilityId);
    void deleteAllByRoomTypeId(Integer roomTypeId);
}
