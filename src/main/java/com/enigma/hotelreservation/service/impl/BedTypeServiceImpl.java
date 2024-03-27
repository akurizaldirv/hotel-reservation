package com.enigma.hotelreservation.service.impl;

import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.entity.BedType;
import com.enigma.hotelreservation.repository.BedTypeRepository;
import com.enigma.hotelreservation.service.BedTypeService;
import com.enigma.hotelreservation.util.exception.QueryException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BedTypeServiceImpl implements BedTypeService {

    private final BedTypeRepository bedTypeRepository;

    @Override
    public BedType getByName(String name) {
        return bedTypeRepository.getBedTypeByName(name.toUpperCase());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public BedType getOrSave(String name) {
        BedType bedType = this.getByName(name);
        if (bedType == null) {
            int rowsChange = bedTypeRepository.insertBedType(name.toUpperCase());
            return this.getByName(name);
        }
        return bedType;
    }
}
