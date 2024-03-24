package com.enigma.hotelreservation.model.entity;


import com.enigma.hotelreservation.constant.DbPath;
import com.enigma.hotelreservation.util.enums.EBedType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = DbPath.BED_TYPE)
@Builder(toBuilder = true)
public class BedType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private EBedType name;

    @OneToMany(mappedBy = "bedType")
    private List<RoomType> roomTypes;
}
