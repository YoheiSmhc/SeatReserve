package com.seat_reserve.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seat_reserve.entity.Reservation;
import com.seat_reserve.repository.ReservationRepository;
import com.seat_reserve.repository.UserRepository;

@Service
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	UserRepository userRepository;

	/*
	ユーザー情報を全検索
	*/
	public List<Reservation> seachAll() {
		return (List<Reservation>) reservationRepository.findAll();

	}

	//	選択した情報をDBに登録。setterで登録する。
	public void completeReservation(LocalDate date, Integer seat, Integer user) {
		//Reserveレポジトリをインスタンス化
		Reservation reservation = new Reservation();
		reservation.setReserveDate(date);
		reservation.setSeatId(seat);
		reservation.setUserId(user);
		LocalDateTime now = LocalDateTime.now();
		reservation.setCreatedAt(now);

		reservationRepository.save(reservation);
	}

	//指定された日付に基づいて予約情報を取得するメソッド
	public List<Reservation> findByDate(LocalDate date) {
		return reservationRepository.findByReserveDate(date);
	}

	//指定された日付とユーザーに基づいて予約情報を取得するメソッド
	//ConfirmationControllerで重複確認するのに使用
	public List<Reservation> findByDateAndUser(LocalDate reserveDate, Integer userId) {
		return reservationRepository.findByReserveDateAndUserId(reserveDate, userId);
	}

	//指定された日付と座席に基づいて予約情報を取得するメソッド
	//削除機能なのか予約機能なのかの分岐に使用
	public List<Reservation> findByDateAndSeat(LocalDate date, Integer seat) {
		return reservationRepository.findByReserveDateAndSeatId(date, seat);
	}

	//指定された日付とユーザーに基づいて予約情報を取得するメソッド
	//ConfirmationControllerで重複確認するのに使用
	public List<Reservation> findByUser(Integer userId) {
		return reservationRepository.findByUserId(userId);
	}

	//指定された日付と座席に基づいて予約情報を取得するメソッド
	//削除機能なのか予約機能なのかの分岐に使用
	public List<Reservation> deleteByReserveId(Integer reserveId) {
		return reservationRepository.deleteByReserveId(reserveId);
	}

	//Userテーブルとreservationsテーブルを結合したうえで情報検索
	//SeatControllerで、Seat.htmlに予約情報を反映させるために使用される
	public List<Reservation> findByDateWithUser(LocalDate date) {
		// 日付に基づいて予約情報を取得
//		List<Reservation> reservations = findByDate(date);

//		// 各予約情報に対して、対応するユーザー情報を取得
//		//懸念点：多数の予約があった場合、N＋１問題が発生する。JOIN句で表結合する方が良い。
//		for (Reservation reservation : reservations) {//取得した予約情報1つ1つに処理する
//			Integer userId = reservation.getUserId();//その予約情報のuserIdを取得
//			Optional<User> userOptional = userRepository.findById(userId);//
//			User user = userOptional.orElse(null); // ユーザーが見つからない場合はnullを返す
//			reservation.setUser(user);//userの情報を予約情報にセットする。
//		}
//
//		return reservations;
		return reservationRepository.findByDateWithUser(date);
	}

	//	 社員IDで検索。
	public List<Reservation> findByUserId(Integer userId) {
		return reservationRepository.findByUserId(userId);
	}

}
