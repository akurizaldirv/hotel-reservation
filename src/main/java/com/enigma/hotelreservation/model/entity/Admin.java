package com.enigma.hotelreservation.model.entity;


import com.enigma.hotelreservation.constant.DbPath;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = DbPath.ADMIN)
@Builder(toBuilder = true)
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_credential_id", nullable = false)
    private UserCredential userCredential;
}
