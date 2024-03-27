package com.enigma.hotelreservation.repository;

import com.enigma.hotelreservation.model.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {

    @Query(value = "SELECT * FROM m_user_credential WHERE id = ?1", nativeQuery = true)
    UserCredential getById(Integer id);

    @Query(value = "SELECT * FROM m_user_credential WHERE username = ?1", nativeQuery = true)
    UserCredential getByUsername(String username);

    @Query(value = "SELECT * FROM m_user_credential ORDER BY id LIMIT 1", nativeQuery = true)
    UserCredential getLastUserCredential();

    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO m_user_credential (username, password, role_id) VALUES (?1, ?2, ?3)", nativeQuery = true)
    int insertUserCredential(String username, String password, Integer role_id);
}
