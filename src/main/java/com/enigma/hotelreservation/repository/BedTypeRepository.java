package com.enigma.hotelreservation.repository;

import com.enigma.hotelreservation.model.entity.BedType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedTypeRepository extends JpaRepository<BedType, Integer> {
}
