package com.enigma.hotelreservation.repository;

import com.enigma.hotelreservation.model.entity.Facility;
import com.enigma.hotelreservation.model.entity.RoomType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
    @Query(value = "SELECT * FROM m_room_type WHERE status = 'ACTIVE'", nativeQuery = true)
    List<Facility> getAll();

    @Query(value = "SELECT * FROM m_room_type WHERE id = ?1 AND status = 'ACTIVE'", nativeQuery = true)
    Facility getFacilityById(Integer id);

    @Query(value = "SELECT * FROM m_room_type  WHERE status = 'ACTIVE' ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Facility getLastFacility();

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "INSERT INTO m_room_type (name, bed_count, bed_type_id) VALUES (?1, ?2, ?3)", nativeQuery = true)
    int insertFacility(String name, Integer bed_count, Integer type_id);

    @Modifying(clearAutomatically = true)
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "UPDATE m_room_type SET name = ?1, bed_count = ?2, bed_type_id = 3 WHERE id = ?4", nativeQuery = true)
    int updateFacility(String name, Integer bed_count, Integer bed_type_id, Integer id);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "UPDATE m_room_type SET status = 'INACTIVE' WHERE id = ?1", nativeQuery = true)
    int deleteFacility(Integer id);
}
