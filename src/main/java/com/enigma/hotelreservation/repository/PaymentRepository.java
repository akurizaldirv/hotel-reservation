package com.enigma.hotelreservation.repository;

import com.enigma.hotelreservation.model.entity.Facility;
import com.enigma.hotelreservation.model.entity.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query(value = "SELECT * FROM t_payment", nativeQuery = true)
    List<Payment> getAll();

    @Query(value = "SELECT p.* FROM t_payment p JOIN t_reservation r ON p.reservation_id = r.id WHERE r.status = ?1", nativeQuery = true)
    List<Payment> getAllByStatus(String status);

    @Query(value = "SELECT * FROM t_payment WHERE id = ?1", nativeQuery = true)
    Payment getPaymentById(Integer id);

    @Query(value = "SELECT * FROM t_payment WHERE reservation_id = ?1 ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Payment getLastPayment(Integer id);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = """
            INSERT INTO t_payment (filename, trx_date, reservation_id) VALUES (
                :filename, :trxDate, :reservationId
            )
            """, nativeQuery = true)
    int insert(String filename, LocalDateTime trxDate, Integer reservationId);
}
