package com.enigma.hotelreservation.repository;

import com.enigma.hotelreservation.model.entity.Room;
import com.enigma.hotelreservation.model.entity.RoomType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query(value = "SELECT * FROM t_room WHERE status = 'ACTIVE'", nativeQuery = true)
    List<Room> getAll();

    @Query(value = "SELECT * FROM t_room WHERE id = ?1", nativeQuery = true)
    Room getRoomById(Integer id);

    @Query(value = "SELECT * FROM t_room WHERE id = ?1 AND status = 'ACTIVE'", nativeQuery = true)
    Room getActiveRoomById(Integer id);

    @Query(value = "SELECT * FROM t_room  WHERE status = 'ACTIVE' ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Room getLastRoom();

    @Query(value = "SELECT * FROM t_room WHERE room_type_id = ?1 AND room_number = ?2 AND status = 'ACTIVE'", nativeQuery = true)
    Room getRoomByRoomTypeIdAndRoomNumber(Integer roomTypeId, Integer roomNumber);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "INSERT INTO t_room (room_type_id, room_number, status) VALUES (?1, ?2, 'ACTIVE')", nativeQuery = true)
    int insertRoom(Integer room_type_id, Integer room_number);

    @Modifying(clearAutomatically = true)
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "UPDATE t_room SET room_type_id = ?1, room_number = ?2 WHERE id = ?3 AND status = 'ACTIVE'", nativeQuery = true)
    int updateRoom(Integer room_type_id, Integer room_number, Integer id);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "UPDATE t_room SET status = 'INACTIVE' WHERE id = ?1", nativeQuery = true)
    int deleteRoom(Integer id);
}
