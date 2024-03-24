package com.enigma.hotelreservation.service.impl;

import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.entity.Facility;
import com.enigma.hotelreservation.model.request.facility.FacilityCreateRequest;
import com.enigma.hotelreservation.model.request.facility.FacilityUpdateRequest;
import com.enigma.hotelreservation.model.response.facility.FacilityResponse;
import com.enigma.hotelreservation.repository.FacilityRepository;
import com.enigma.hotelreservation.service.FacilityService;
import com.enigma.hotelreservation.util.exception.DataNotFoundException;
import com.enigma.hotelreservation.util.exception.QueryException;
import com.enigma.hotelreservation.util.mapper.FacilityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;

    @Override
    public List<FacilityResponse> getAll() {
        return facilityRepository.getAll().stream().map(FacilityMapper::mapToRes).toList();
    }

    @Override
    public FacilityResponse getById(Integer id) {
        return FacilityMapper.mapToRes(this.getFacilityById(id));
    }

    @Override
    public Facility getFacilityById(Integer id) {
        Facility facility = facilityRepository.getFacilityById(id);
        if (facility == null) throw new DataNotFoundException(ResponseMessage.DATA_NOT_FOUND);
        return facility;
    }

    @Override
    public Facility getLastFacility() {
        return facilityRepository.getLastFacility();
    }

    @Override
    public FacilityResponse create(FacilityCreateRequest request) {
        int rowsChange = facilityRepository.insertFacility(request.getName());
        if (rowsChange == 0) throw new QueryException(ResponseMessage.CREATE_DATA_FAILED);

        return FacilityMapper.mapToRes(this.getLastFacility());
    }

    @Override
    public FacilityResponse update(FacilityUpdateRequest request) {
        if (!this.isExist(request.getId())) throw new DataNotFoundException(ResponseMessage.DATA_NOT_FOUND);
        int rowsChange = facilityRepository.updateFacility(request.getName(), request.getId());
        if (rowsChange == 0) throw new QueryException(ResponseMessage.UPDATE_DATA_FAILED);

        return FacilityMapper.mapToRes(this.getFacilityById(request.getId()));
    }

    @Override
    public void delete(Integer id) {
        if (!this.isExist(id)) throw new DataNotFoundException(ResponseMessage.DATA_NOT_FOUND);
        int rowsChange = facilityRepository.deleteFacility(id);
        if (rowsChange == 0) throw new QueryException(ResponseMessage.DELETE_DATA_FAILED);
    }

    @Override
    public Boolean isExist(Integer id) {
        Facility facility = facilityRepository.getFacilityById(id);
        return facility != null;
    }
}
