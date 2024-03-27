package com.enigma.hotelreservation.repository;

import com.enigma.hotelreservation.model.entity.Admin;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query(value = "SELECT * FROM m_admin", nativeQuery = true)
    List<Admin> getAll();

    @Query(value = "SELECT * FROM m_admin WHERE id = ?1", nativeQuery = true)
    Admin getById(Integer id);

    @Query(value = "SELECT * FROM m_admin ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Admin getLastAdmin();

    @Query(value = "SELECT COUNT(*) FROM m_admin WHERE email = :email OR phone_number = :phone", nativeQuery = true)
    int checkValidation(String email, String phone);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "INSERT INTO m_admin (address, email, phone_number, name, user_credential_id) VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    int insertAdmin(String address, String email, String phoneNumber, String name, Integer userCredentialId);
}
