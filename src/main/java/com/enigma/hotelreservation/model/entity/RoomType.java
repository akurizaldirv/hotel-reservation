package com.enigma.hotelreservation.model.entity;


import com.enigma.hotelreservation.constant.DbPath;
import com.enigma.hotelreservation.util.enums.EStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = DbPath.ROOM_TYPE)
@Builder(toBuilder = true)
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "bed_count")
    private Integer bedCount;

    @ManyToOne
    @JoinColumn(name = "bed_type_id", nullable = false)
    private BedType bedType;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private EStatus status;

    @OneToMany(mappedBy = "roomType")
    private List<RoomFacility> roomFacilities;

    @OneToMany(mappedBy = "roomType")
    private List<Room> rooms;
}
