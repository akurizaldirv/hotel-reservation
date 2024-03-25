package com.enigma.hotelreservation.repository;

import com.enigma.hotelreservation.model.entity.RoomPrice;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomPriceRepository extends JpaRepository<RoomPrice, Integer> {
    @Query(value = "SELECT * FROM t_room_price WHERE id = ?1", nativeQuery = true)
    RoomPrice getRoomPriceById(Integer id);

    @Query(value = "SELECT * FROM t_room_price WHERE id = ?1 AND status = 'ACTIVE'", nativeQuery = true)
    RoomPrice getActiveRoomPriceById(Integer id);
    @Query(value = "SELECT * FROM t_room_price WHERE room_id = ?1", nativeQuery = true)
    RoomPrice getRoomPriceByRoomId(Integer id);

    @Query(value = "SELECT * FROM t_room_price WHERE room_id = ?1 AND status = 'ACTIVE'", nativeQuery = true)
    RoomPrice getActiveRoomPriceByRoomId(Integer id);

    @Query(value = "SELECT * FROM t_room_price  WHERE status = 'ACTIVE' ORDER BY id DESC LIMIT 1", nativeQuery = true)
    RoomPrice getLastRoomPrice();

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "INSERT INTO t_room_price (room_id, price, status) VALUES (?1, ?2, 'ACTIVE')", nativeQuery = true)
    int insertRoomPrice(Integer room_id, Long price);

    @Modifying(clearAutomatically = true)
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "UPDATE t_room_price SET room_id = ?1, price = ?2, WHERE id = ?3", nativeQuery = true)
    int updateRoomPrice(Integer room_id, Long price, Integer id);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "UPDATE t_room_price SET status = 'INACTIVE' WHERE room_id = ?1", nativeQuery = true)
    int deleteRoomPrice(Integer id);
}
