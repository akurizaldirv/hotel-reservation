package com.enigma.hotelreservation.service.impl;

import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.entity.Role;
import com.enigma.hotelreservation.repository.RoleRepository;
import com.enigma.hotelreservation.service.RoleService;
import com.enigma.hotelreservation.util.enums.ERole;
import com.enigma.hotelreservation.util.exception.QueryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getByName(ERole role) {
        return roleRepository.getRoleByName(role.name());
    }

    @Override
    public Role getOrSave(ERole role) {
        Role newRole = this.getByName(role);

        if (newRole == null) {
            int rowsChange = roleRepository.insertRole(role.name());
            if (rowsChange == 0) throw new QueryException(ResponseMessage.CREATE_DATA_FAILED);
            return this.getByName(role);
        }
        return newRole;
    }
}
