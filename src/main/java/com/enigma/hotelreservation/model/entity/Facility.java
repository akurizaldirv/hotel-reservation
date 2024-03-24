package com.enigma.hotelreservation.model.entity;

import com.enigma.hotelreservation.constant.DbPath;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = DbPath.FACILITY)
@Builder(toBuilder = true)
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "facility")
    private List<RoomFacility> roomFacilities;
}
