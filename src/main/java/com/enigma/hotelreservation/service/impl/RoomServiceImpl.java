package com.enigma.hotelreservation.service.impl;

import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.entity.Room;
import com.enigma.hotelreservation.model.entity.RoomPrice;
import com.enigma.hotelreservation.model.entity.RoomType;
import com.enigma.hotelreservation.model.request.room.RoomCreateRequest;
import com.enigma.hotelreservation.model.request.room.RoomUpdateRequest;
import com.enigma.hotelreservation.model.response.room.RoomResponse;
import com.enigma.hotelreservation.model.response.roomtype.RoomTypeIdNameResponse;
import com.enigma.hotelreservation.repository.RoomPriceRepository;
import com.enigma.hotelreservation.repository.RoomRepository;
import com.enigma.hotelreservation.service.RoomService;
import com.enigma.hotelreservation.service.RoomTypeService;
import com.enigma.hotelreservation.util.exception.DataNotFoundException;
import com.enigma.hotelreservation.util.exception.QueryException;
import com.enigma.hotelreservation.util.mapper.RoomMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomPriceRepository roomPriceRepository;
    private final RoomTypeService roomTypeService;


    @Override
    public List<RoomResponse> getAll() {
        List<Room> rooms = roomRepository.getAll();

        return rooms.stream().map(room -> {
            RoomPrice roomPrice = roomPriceRepository.getActiveRoomPriceByRoomId(room.getId());
            RoomTypeIdNameResponse roomTypeIdNameResponse = roomTypeService.getIdNameById(room.getRoomType().getId());
            return RoomMapper.mapToRes(room, roomTypeIdNameResponse, roomPrice.getPrice());
        }).toList();
    }

    @Override
    public RoomResponse getById(Integer id) {
        Room room = this.getActiveRoomById(id);
        RoomPrice roomPrice = roomPriceRepository.getActiveRoomPriceByRoomId(room.getId());
        RoomTypeIdNameResponse roomTypeIdNameResponse = roomTypeService.getIdNameById(room.getRoomType().getId());
        return RoomMapper.mapToRes(room, roomTypeIdNameResponse, roomPrice.getPrice());
    }

    @Override
    public Room getRoomById(Integer id) {
        Room room = roomRepository.getRoomById(id);
        if (room == null) throw new DataNotFoundException(ResponseMessage.DATA_NOT_FOUND);
        return room;
    }

    @Override
    public Room getActiveRoomById(Integer id) {
        Room room = roomRepository.getActiveRoomById(id);
        if (room == null) throw new DataNotFoundException(ResponseMessage.DATA_NOT_FOUND);
        return room;
    }

    @Override
    public RoomPrice getActiveRoomPriceByRoomId(Integer id) {
        RoomPrice roomPrice = roomPriceRepository.getActiveRoomPriceByRoomId(id);
        if (roomPrice == null) throw new DataNotFoundException(ResponseMessage.DATA_NOT_FOUND);
        return roomPrice;
    }

    @Override
    public Room getLastRoom() {
        return roomRepository.getLastRoom();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RoomResponse create(RoomCreateRequest request) {
        if (roomRepository.getRoomByRoomTypeIdAndRoomNumber(request.getRoomTypeId(), request.getRoomNumber()) != null) {
            throw new ValidationException("Duplicate Room Type ID and Room Number");
        }

        RoomType roomType = roomTypeService.getRoomTypeById(request.getRoomTypeId());
        int rowsChange = roomRepository.insertRoom(roomType.getId(), request.getRoomNumber());
        if (rowsChange == 0) throw new QueryException(ResponseMessage.CREATE_DATA_FAILED);
        Room room = this.getLastRoom();

        rowsChange = roomPriceRepository.insertRoomPrice(room.getId(), request.getPrice());
        if (rowsChange == 0) throw new QueryException(ResponseMessage.CREATE_DATA_FAILED);
        RoomPrice roomPrice = roomPriceRepository.getActiveRoomPriceByRoomId(room.getId());

        RoomTypeIdNameResponse roomTypeIdNameResponse = roomTypeService.getIdNameById(room.getRoomType().getId());
        return RoomMapper.mapToRes(room, roomTypeIdNameResponse, roomPrice.getPrice());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RoomResponse update(RoomUpdateRequest request) {
        Room room = this.getActiveRoomById(request.getId());
        if (!roomTypeService.isExist(request.getRoomTypeId())) throw new DataNotFoundException("Room Type ID not found");

        Room duplicateRoom = roomRepository.getRoomByRoomTypeIdAndRoomNumber(request.getRoomTypeId(), request.getRoomNumber());
        if (duplicateRoom != null) {
            if (duplicateRoom.getId() != request.getId()) {
                throw new ValidationException("Duplicate Room Type ID and Room Number");
            }
        }

        int rowsChange = roomRepository.updateRoom(request.getRoomTypeId(), request.getRoomNumber(), request.getId());
        if (rowsChange == 0) throw new QueryException(ResponseMessage.UPDATE_DATA_FAILED);

        rowsChange = roomPriceRepository.deleteRoomPrice(request.getId());
        if (rowsChange == 0) throw new QueryException(ResponseMessage.UPDATE_DATA_FAILED);

        rowsChange = roomPriceRepository.insertRoomPrice(request.getId(), request.getPrice());
        if (rowsChange == 0) throw new QueryException(ResponseMessage.UPDATE_DATA_FAILED);

        RoomTypeIdNameResponse roomTypeIdNameResponse = roomTypeService.getIdNameById(room.getRoomType().getId());
        RoomPrice roomPrice = roomPriceRepository.getActiveRoomPriceByRoomId(room.getId());

        return RoomMapper.mapToRes(room, roomTypeIdNameResponse, roomPrice.getPrice());
    }

    @Override
    public void delete(Integer id) {
        if (!this.isExist(id)) throw new DataNotFoundException(ResponseMessage.DATA_NOT_FOUND);

        int rowsChange = roomPriceRepository.deleteRoomPrice(id);
        if (rowsChange == 0) throw new QueryException(ResponseMessage.DELETE_DATA_FAILED);

        rowsChange = roomRepository.deleteRoom(id);
        if (rowsChange == 0) throw new QueryException(ResponseMessage.DELETE_DATA_FAILED);
    }

    @Override
    public Boolean isExist(Integer id) {
        Room room = roomRepository.getActiveRoomById(id);
        return room != null;
    }
}
