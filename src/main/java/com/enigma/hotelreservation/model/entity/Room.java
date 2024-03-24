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
@Entity(name = DbPath.ROOM)
@Builder(toBuilder = true)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomType;

    @Column(name = "room_number", nullable = false)
    private Integer roomNumber;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private EStatus status;

    @OneToMany(mappedBy = "room")
    private List<RoomPrice> roomPrices;
}
