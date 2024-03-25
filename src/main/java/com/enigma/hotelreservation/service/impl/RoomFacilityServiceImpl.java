package com.enigma.hotelreservation.service.impl;

import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.entity.RoomFacility;
import com.enigma.hotelreservation.repository.RoomFacilityRepository;
import com.enigma.hotelreservation.service.RoomFacilityService;
import com.enigma.hotelreservation.util.exception.QueryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomFacilityServiceImpl implements RoomFacilityService {
    private final RoomFacilityRepository roomFacilityRepository;

    @Override
    public List<RoomFacility> getAllByRoomTypeId(Integer id) {
        return roomFacilityRepository.getAllByRoomTypeId(id);
    }

    @Override
    public RoomFacility insertRoomFacility(Integer roomTypeId, Integer facilityId) {
        int rowsChange = roomFacilityRepository.insertRoomFacility(roomTypeId, facilityId);
        if (rowsChange == 0) throw new QueryException(ResponseMessage.CREATE_DATA_FAILED);

        return roomFacilityRepository.getByRoomTypeIdAndFacilityId(roomTypeId, facilityId);
    }

    @Override
    public void deleteAllByRoomTypeId(Integer roomTypeId) {
        int rowsChange = roomFacilityRepository.deleteAllByRoomTypeId(roomTypeId);
    }
}
