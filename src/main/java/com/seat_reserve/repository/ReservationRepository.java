package com.seat_reserve.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seat_reserve.entity.Reservation;

/**
*メンバー情報 Repository
*/

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer>{
//	 @Query("SELECT r.reserveID, r.userId, r.seatId, r.reserveDate, u.username " +
//	           "FROM Reservation r " +
//	           "JOIN User u ON r.userId = u.userId " +
//	           "WHERE r.userId = :userId")
	List<Reservation>findByUserId(Integer userId);
    List<Reservation> findByReserveDate(LocalDate reserveDate);
    List<Reservation> findByReserveDateAndUserId(LocalDate reserveDate,Integer userId);
    List<Reservation> findByReserveDateAndSeatId(LocalDate reserveDate,Integer seatId);
//    reservationIDを元に削除する
    List<Reservation> deleteByReserveId(Integer reserveId);
}

