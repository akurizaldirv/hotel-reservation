package com.enigma.hotelreservation.service;

import com.enigma.hotelreservation.model.entity.BedType;

public interface BedTypeService {
    BedType getOrSave(String name);
    BedType getByName(String name);
}
