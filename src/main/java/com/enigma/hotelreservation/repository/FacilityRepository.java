package com.enigma.hotelreservation.repository;

import com.enigma.hotelreservation.model.entity.Facility;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Integer> {
    @Query(value = "SELECT * FROM m_facility WHERE status = 'ACTIVE'", nativeQuery = true)
    List<Facility> getAll();

    @Query(value = "SELECT * FROM m_facility WHERE id = ?1", nativeQuery = true)
    Facility getFacilityById(Integer id);

    @Query(value = "SELECT * FROM m_facility WHERE id = ?1 AND status = 'ACTIVE'", nativeQuery = true)
    Facility getActiveFacilityById(Integer id);

    @Query(value = "SELECT * FROM m_facility WHERE status = 'ACTIVE' ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Facility getLastFacility();

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "INSERT INTO m_facility (name, status) VALUES (?1, 'ACTIVE')", nativeQuery = true)
    int insertFacility(String name);

    @Modifying(clearAutomatically = true)
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "UPDATE m_facility SET name = ?1 WHERE id = ?2 AND status = 'ACTIVE'", nativeQuery = true)
    int updateFacility(String name, Integer id);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "UPDATE m_facility SET status = 'INACTIVE' WHERE id = ?1", nativeQuery = true)
    int deleteFacility(Integer id);
}
