package com.enigma.hotelreservation.service.impl;

import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.entity.Admin;
import com.enigma.hotelreservation.model.entity.UserCredential;
import com.enigma.hotelreservation.model.request.auth.RegisterAdminRequest;
import com.enigma.hotelreservation.repository.AdminRepository;
import com.enigma.hotelreservation.service.AdminService;
import com.enigma.hotelreservation.util.exception.DataNotFoundException;
import com.enigma.hotelreservation.util.exception.QueryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public List<Admin> getAll() {
        return adminRepository.getAll();
    }

    @Override
    public Admin getAdminById(Integer id) {
        Admin admin = adminRepository.getById(id);
        if (admin == null) throw new DataNotFoundException(ResponseMessage.DATA_NOT_FOUND);
        return admin;
    }

    @Override
    public Admin getLastAdmin() {
        return adminRepository.getLastAdmin();
    }

    @Override
    public Admin create(RegisterAdminRequest request, UserCredential userCredential) {
        int rowsChange = adminRepository.insertAdmin(
                request.getAddress(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getName(),
                userCredential.getId()
        );
        if (rowsChange == 0) throw new QueryException(ResponseMessage.CREATE_DATA_FAILED);

        return this.getLastAdmin();
    }
}
