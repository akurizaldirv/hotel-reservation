package com.enigma.hotelreservation.repository;

import com.enigma.hotelreservation.model.entity.RoomFacility;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomFacilityRepository extends JpaRepository<RoomFacility, Integer> {
    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query(value = "DELETE FROM m_room_facility WHERE room_type_id = ?1", nativeQuery = true)
    int deleteAllByRoomTypeId(Integer id);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "INSERT INTO m_room_facility (room_type_id, facility_id) VALUES (?1, ?2)", nativeQuery = true)
    int insertRoomFacility(Integer roomTypeId, Integer facilityId);

    @Query(value = "SELECT * FROM m_room_facility WHERE room_type_id = ?1", nativeQuery = true)
    List<RoomFacility> getAllByRoomTypeId(Integer id);

    @Query(value = "SELECT * FROM m_room_facility WHERE room_type_id = ?1 AND facility_id = ?2", nativeQuery = true)
    RoomFacility getByRoomTypeIdAndFacilityId(Integer roomTypeId, Integer facilityId);
}
