package com.enigma.hotelreservation.service;

import com.enigma.hotelreservation.model.entity.Role;
import com.enigma.hotelreservation.util.enums.ERole;

public interface RoleService {
    Role getByName(ERole role);
    Role getOrSave(ERole role);
}
