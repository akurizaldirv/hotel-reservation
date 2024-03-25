package com.enigma.hotelreservation.repository;

import com.enigma.hotelreservation.model.entity.Role;
import com.enigma.hotelreservation.util.enums.ERole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "SELECT * FROM m_role WHERE role = ?1", nativeQuery = true)
    Role getRoleByName(String role);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "INSERT INTO m_role (role) VALUES (?1)", nativeQuery = true)
    int insertRole(String role);
}
