package com.enigma.hotelreservation.service;

import com.enigma.hotelreservation.model.entity.Facility;
import com.enigma.hotelreservation.model.request.facility.FacilityCreateRequest;
import com.enigma.hotelreservation.model.request.facility.FacilityUpdateRequest;
import com.enigma.hotelreservation.model.response.facility.FacilityResponse;

import java.util.List;

public interface FacilityService {
    List<FacilityResponse> getAll();
    FacilityResponse getById(Integer id);
    Facility getFacilityById(Integer id);
    Facility getActiveFacilityById(Integer id);
    Facility getLastFacility();
    FacilityResponse create(FacilityCreateRequest request);
    FacilityResponse update(FacilityUpdateRequest request);
    void delete(Integer id);
    Boolean isExist(Integer id);
}
