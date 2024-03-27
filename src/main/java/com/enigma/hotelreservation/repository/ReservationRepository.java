package com.enigma.hotelreservation.repository;

import com.enigma.hotelreservation.model.entity.Reservation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query(value = "SELECT * FROM t_reservation WHERE id = :id", nativeQuery = true)
    Reservation getById(Integer id);

    @Query(value = "SELECT * FROM t_reservation", nativeQuery = true)
    List<Reservation> getAll();

    @Query(value = "SELECT * FROM t_reservation WHERE status = :status", nativeQuery = true)
    List<Reservation> getAllByStatus(String status);

    @Query(value = "SELECT * FROM t_reservation ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Reservation getLastReservation();

    @Query(value = """
                SELECT COUNT(*)
                FROM t_reservation rsv JOIN t_room_price rp ON rsv.room_price_id = rp.id\n
                JOIN t_room r ON rp.room_id = r.id
                WHERE r.id = :roomId AND
                rsv.status IN ('CHECKING', 'PAYMENT', 'SUCCESS') AND
                :date BETWEEN checkin_date AND checkout_date
            """, nativeQuery = true)
    int getCountByDateBetween(LocalDate date, Integer roomId);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = """
            INSERT INTO t_reservation (checkout_date, checkin_date, customer_id, room_price_id, status) VALUES (
                :checkoutDate,
                :checkinDate,
                :customerId,
                :roomPriceId,
                'PAYMENT'
            )
            """, nativeQuery = true)
    int insert(LocalDate checkoutDate, LocalDate checkinDate, Integer customerId, Integer roomPriceId);

    @Modifying(clearAutomatically = true)
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "UPDATE t_reservation SET status = :status WHERE id = :id", nativeQuery = true)
    int updateStatus(String status, Integer id);
}
