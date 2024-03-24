package com.enigma.hotelreservation.repository;

import com.enigma.hotelreservation.model.entity.BedType;
import com.enigma.hotelreservation.model.entity.Facility;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BedTypeRepository extends JpaRepository<BedType, Integer> {
    @Query(value = "SELECT * FROM m_bed_type WHERE name = ?1", nativeQuery = true)
    BedType getBedTypeByName(String name);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "INSERT INTO m_bed_type (name) VALUES (?1)", nativeQuery = true)
    int insertBedType(String name);
}
