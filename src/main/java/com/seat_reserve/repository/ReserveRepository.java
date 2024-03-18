package com.seat_reserve.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seat_reserve.entity.Reserve;

/**
*メンバー情報 Repository
*/

@Repository
public interface ReserveRepository extends JpaRepository<Reserve,Integer>{
//	 @Query("SELECT r.reserveID, r.userId, r.seatId, r.reserveDate, u.username " +
//	           "FROM Reserve r " +
//	           "JOIN User u ON r.userId = u.userId " +
//	           "WHERE r.userId = :userId")
	List<Reserve>findByUserId(Integer userId);
    List<Reserve> findByReserveDate(LocalDate reserveDate);
    List<Reserve> findByReserveDateAndUserId(LocalDate reserveDate,Integer userId);
    List<Reserve> findByReserveDateAndSeatId(LocalDate reserveDate,Integer seatId);
//    reservationIDを元に削除する
    List<Reserve> deleteByReserveId(Integer reserveId);
}

