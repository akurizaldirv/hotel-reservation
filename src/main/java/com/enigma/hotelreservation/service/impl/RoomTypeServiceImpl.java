package com.enigma.hotelreservation.service.impl;

import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.entity.BedType;
import com.enigma.hotelreservation.model.entity.Facility;
import com.enigma.hotelreservation.model.entity.RoomFacility;
import com.enigma.hotelreservation.model.entity.RoomType;
import com.enigma.hotelreservation.model.request.roomtype.RoomTypeCreateRequest;
import com.enigma.hotelreservation.model.request.roomtype.RoomTypeUpdateRequest;
import com.enigma.hotelreservation.model.response.roomtype.RoomTypeResponse;
import com.enigma.hotelreservation.repository.RoomFacilityRepository;
import com.enigma.hotelreservation.repository.RoomTypeRepository;
import com.enigma.hotelreservation.service.BedTypeService;
import com.enigma.hotelreservation.service.FacilityService;
import com.enigma.hotelreservation.service.RoomTypeService;
import com.enigma.hotelreservation.util.exception.DataNotFoundException;
import com.enigma.hotelreservation.util.exception.QueryException;
import com.enigma.hotelreservation.util.mapper.FacilityMapper;
import com.enigma.hotelreservation.util.mapper.RoomTypeMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;
    private final RoomFacilityRepository roomFacilityRepository;
    private final FacilityService facilityService;
    private final BedTypeService bedTypeService;

    @Override
    public List<RoomTypeResponse> getAll() {
        List<RoomType> roomTypes = roomTypeRepository.getAll();
        List<RoomTypeResponse> roomTypeResponses = roomTypes.stream().map(roomType -> {
            List<RoomFacility> roomFacilities = roomFacilityRepository.getAllByRoomTypeId(roomType.getId());
            List<String> facilities = roomFacilities.stream().map(facility ->
                facilityService.getFacilityById(facility.getFacility().getId()).getName()
            ).toList();

            return RoomTypeMapper.mapToRes(roomType, facilities);
        }).toList();
        return roomTypeResponses;
    }

    @Override
    public RoomTypeResponse getById(Integer id) {
        RoomType roomType = this.getRoomTypeById(id);
        List<RoomFacility> roomFacilities = roomFacilityRepository.getAllByRoomTypeId(roomType.getId());
        List<String> facilities = roomFacilities.stream().map(facility ->
                facilityService.getFacilityById(facility.getFacility().getId()).getName()
        ).toList();
        return RoomTypeMapper.mapToRes(roomType, facilities);
    }

    @Override
    public RoomType getRoomTypeById(Integer id) {
        RoomType roomType = roomTypeRepository.getRoomTypeById(id);
        if (roomType == null) throw new DataNotFoundException(ResponseMessage.DATA_NOT_FOUND);
        return roomType;
    }

    @Override
    public RoomType getLastRoomType() {
        return roomTypeRepository.getLastRoomType();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RoomTypeResponse create(RoomTypeCreateRequest request) {
        BedType bedType = bedTypeService.getOrSave(request.getBedType());
        int rowsChange = roomTypeRepository.insertRoomType(request.getName(), request.getBedCount(), bedType.getId());
        if (rowsChange == 0) throw new QueryException(ResponseMessage.CREATE_DATA_FAILED);
        RoomType roomType = this.getLastRoomType();

        for (Integer id : request.getFacilitiesId()) {
            if (!facilityService.isExist(id)) throw new DataNotFoundException("Facility ID not found");
            rowsChange = roomFacilityRepository.insertRoomFacility(roomType.getId(), id);
            if (rowsChange == 0) throw new QueryException(ResponseMessage.CREATE_DATA_FAILED);
        }

        List<RoomFacility> roomFacilities = roomFacilityRepository.getAllByRoomTypeId(roomType.getId());
        List<String> facilities = roomFacilities.stream().map(facility ->
                facilityService.getFacilityById(facility.getFacility().getId()).getName()
        ).toList();
        return RoomTypeMapper.mapToRes(roomType, facilities);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RoomTypeResponse update(RoomTypeUpdateRequest request) {
        if (!this.isExist(request.getId())) throw new DataNotFoundException(ResponseMessage.DATA_NOT_FOUND);
        BedType bedType = bedTypeService.getOrSave(request.getBedType());
        int rowsChange = roomTypeRepository.updateRoomType(
                request.getName(),
                request.getBedCount(),
                bedType.getId(),
                request.getId()
        );
        if (rowsChange == 0) throw new QueryException(ResponseMessage.CREATE_DATA_FAILED);
        RoomType roomType = this.getLastRoomType();

        roomFacilityRepository.deleteAllByRoomTypeId(roomType.getId());
        for (Integer id : request.getFacilitiesId()) {
            rowsChange = roomFacilityRepository.insertRoomFacility(roomType.getId(), id);
            if (rowsChange == 0) throw new QueryException(ResponseMessage.CREATE_DATA_FAILED);
        }
        List<RoomFacility> roomFacilities = roomFacilityRepository.getAllByRoomTypeId(roomType.getId());
        List<String> facilities = roomFacilities.stream().map(roomFacility ->
                        facilityService.getFacilityById(roomFacility.getFacility().getId()).getName()
                ).toList();

        return RoomTypeMapper.mapToRes(roomType, facilities);
    }

    @Override
    public void delete(Integer id) {
        if (!this.isExist(id)) throw new DataNotFoundException(ResponseMessage.DATA_NOT_FOUND);
        int rowsChange = roomTypeRepository.deleteRoomType(id);
        if (rowsChange == 0) throw new QueryException(ResponseMessage.DELETE_DATA_FAILED);
    }

    @Override
    public Boolean isExist(Integer id) {
        RoomType roomType = roomTypeRepository.getRoomTypeById(id);
        return roomType != null;
    }
}
