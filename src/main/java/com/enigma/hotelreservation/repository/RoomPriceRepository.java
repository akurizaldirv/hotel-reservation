package com.enigma.hotelreservation.repository;

import com.enigma.hotelreservation.model.entity.RoomPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomPriceRepository extends JpaRepository<RoomPrice, Integer> {
}
