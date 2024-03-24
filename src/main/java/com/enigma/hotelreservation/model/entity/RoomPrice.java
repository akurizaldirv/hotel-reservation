package com.enigma.hotelreservation.model.entity;


import com.enigma.hotelreservation.constant.DbPath;
import com.enigma.hotelreservation.util.enums.EStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = DbPath.ROOM_PRICE)
@Builder(toBuilder = true)
public class RoomPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private EStatus status;

}
