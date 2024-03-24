package com.enigma.hotelreservation.repository;

import com.enigma.hotelreservation.model.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {
}
