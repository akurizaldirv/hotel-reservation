package com.enigma.hotelreservation.service.impl;

import com.enigma.hotelreservation.constant.ResponseMessage;
import com.enigma.hotelreservation.model.entity.Admin;
import com.enigma.hotelreservation.model.entity.UserCredential;
import com.enigma.hotelreservation.model.request.auth.RegisterAdminRequest;
import com.enigma.hotelreservation.model.request.user.AdminUpdateRequest;
import com.enigma.hotelreservation.model.response.admin.AdminResponse;
import com.enigma.hotelreservation.repository.AdminRepository;
import com.enigma.hotelreservation.service.AdminService;
import com.enigma.hotelreservation.util.exception.DataNotFoundException;
import com.enigma.hotelreservation.util.exception.QueryException;
import com.enigma.hotelreservation.util.mapper.AdminMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public List<AdminResponse> getAll() {
        List<Admin> admins = adminRepository.getAll();
        return admins.stream().map(AdminMapper::mapToRes).toList();
    }

    @Override
    public AdminResponse getById(Integer id) {
        return AdminMapper.mapToRes(this.getAdminById(id));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public AdminResponse update(AdminUpdateRequest request) {
        Admin admin = this.getAdminById(request.getId());

        int rowsChange = adminRepository.updateAdmin(
                request.getAddress(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getName(),
                admin.getUserCredential().getId(),
                request.getId()
                );
        if (rowsChange == 0) throw new QueryException(ResponseMessage.UPDATE_DATA_FAILED);
        return AdminMapper.mapToRes(this.getAdminById(request.getId()));
    }

    @Override
    public Admin getAdminById(Integer id) {
        Admin admin = adminRepository.getById(id);
        if (admin == null) throw new DataNotFoundException(ResponseMessage.ADMIN_NOT_FOUND);
        return admin;
    }

    @Override
    public Admin getLastAdmin() {
        return adminRepository.getLastAdmin();
    }

    @Override
    public Admin create(RegisterAdminRequest request, UserCredential userCredential) {
        int validation = adminRepository.checkValidation(request.getEmail(), request.getPhoneNumber());
        if (validation > 0) throw new ValidationException("Email, or Phone Number has been taken");
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
