package com.enigma.hotelreservation.service;

import com.enigma.hotelreservation.model.entity.Admin;
import com.enigma.hotelreservation.model.entity.UserCredential;
import com.enigma.hotelreservation.model.request.auth.RegisterAdminRequest;
import com.enigma.hotelreservation.model.request.user.AdminUpdateRequest;
import com.enigma.hotelreservation.model.response.admin.AdminResponse;

import java.util.List;

public interface AdminService {
    List<AdminResponse> getAll();
    AdminResponse getById(Integer id);
    AdminResponse update(AdminUpdateRequest request);
    Admin getAdminById(Integer id);
    Admin getLastAdmin();
    Admin create(RegisterAdminRequest request, UserCredential userCredential);
}
