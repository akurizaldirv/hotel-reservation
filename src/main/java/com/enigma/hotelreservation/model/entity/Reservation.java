package com.enigma.hotelreservation.model.entity;


import com.enigma.hotelreservation.constant.DbPath;
import com.enigma.hotelreservation.util.enums.EReservationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = DbPath.RESERVATION)
@Builder(toBuilder = true)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "checkin_date", nullable = false)
    private LocalDateTime checkinDate;

    @Column(name = "checkout_date", nullable = false)
    private LocalDateTime checkoutDate;

    @Enumerated(value = EnumType.STRING)
    private EReservationStatus status;

    @OneToOne(mappedBy = "reservation")
    private Payment payment;

}
