package com.enigma.hotelreservation.service;

import com.enigma.hotelreservation.model.entity.Admin;
import com.enigma.hotelreservation.model.entity.UserCredential;
import com.enigma.hotelreservation.model.request.auth.RegisterAdminRequest;

import java.util.List;

public interface AdminService {
    List<Admin> getAll();
    Admin getAdminById(Integer id);
    Admin getLastAdmin();
    Admin create(RegisterAdminRequest request, UserCredential userCredential);
}
