package com.seat_reserve.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.seat_reserve.entity.Reservation;
import com.seat_reserve.service.ReservationService;

@Controller
public class SeatController {

	@GetMapping("/seat")
	public String getSeat() {
		return "seat";
	}

	@Autowired
	ReservationService reservationService;

	// 指定された日付に基づいて予約情報を取得し、JSON形式で返す。予約情報反映のため。
	@GetMapping("/reservations")
	public ResponseEntity<List<Reservation>> getReservationsByDate(
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		// List<Reservation> reservations = reservationService.findByDate(date);

		List<Reservation> reservations = reservationService.findByDateWithUser(date);
		//HTTPレスポンスのボディ部分に"reservations"がJSON形式で格納される
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}

}
