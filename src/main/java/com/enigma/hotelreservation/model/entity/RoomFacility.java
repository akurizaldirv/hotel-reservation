package com.enigma.hotelreservation.model.entity;


import com.enigma.hotelreservation.constant.DbPath;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = DbPath.ROOM_FACILTY)
@Builder(toBuilder = true)
public class RoomFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "facility_id")
    private Facility facility;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "room_type_id")
    private RoomType roomType;
}
