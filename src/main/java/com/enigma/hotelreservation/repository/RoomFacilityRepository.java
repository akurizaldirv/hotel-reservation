package com.enigma.hotelreservation.repository;

import com.enigma.hotelreservation.model.entity.RoomFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomFacilityRepository extends JpaRepository<RoomFacility, Integer> {
}
