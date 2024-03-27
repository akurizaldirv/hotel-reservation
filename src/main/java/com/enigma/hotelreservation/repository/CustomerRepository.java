package com.enigma.hotelreservation.repository;

import com.enigma.hotelreservation.model.entity.Admin;
import com.enigma.hotelreservation.model.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "SELECT * FROM m_customer", nativeQuery = true)
    List<Customer> getAll();

    @Query(value = "SELECT * FROM m_customer WHERE id = ?1", nativeQuery = true)
    Customer getById(Integer id);

    @Query(value = "SELECT * FROM m_customer ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Customer getLastCustomer();

    @Query(value = "SELECT COUNT(*) FROM m_customer WHERE email = :email OR identity_number = :idNumber OR phone_number = :phone", nativeQuery = true)
    int checkValidation(String email, String idNumber, String phone);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "INSERT INTO m_customer (address, email, phone_number, name, identity_number, user_credential_id) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    int insertCustomer(String address, String email, String phoneNumber, String name, String identityNumber, Integer userCredentialId);
}
