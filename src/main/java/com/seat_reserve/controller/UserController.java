package com.seat_reserve.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.seat_reserve.entity.Reservation;
import com.seat_reserve.entity.User;
import com.seat_reserve.service.ReservationService;
import com.seat_reserve.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	ReservationService reservationService;

	//	user.htmlに、メンバー一覧情報を渡す
	@GetMapping("/user")
	public String displayUserOrDelete(
			@RequestParam(name = "seatId", required = false) Integer seat,
			@RequestParam(name = "reservationDate", required = false) LocalDate date,
			Model model) {
		//seatとdateを元に、reservationsテーブル内を検索し、ListByDateAndSeatに格納
		List<Reservation> ListByDateAndSeat = reservationService.findByDateAndSeat(date, seat);
		//ListByDateAndSeatにデータがあったら削除機能、なければuser.htmlを介して予約機能もしくは予約情報一覧表示機能に遷移する
		//seatとdateがあったら削除機能、なければuser.htmlを介して予約機能
		if (ListByDateAndSeat == null || ListByDateAndSeat.isEmpty()) {
			//ListByDateAndSeatが空、つまりはまだその日付のその座席が空もしくはメンバーアイコンを押してきてdateとseatがnullだった場合、こちらの処理

			model.addAttribute("seat", seat);
			model.addAttribute("date", date);
			//メンバー一覧を取得し、userlistオブジェクトに格納
			List<User> userlist = userService.seachAll();
			model.addAttribute("userlist", userlist);
			return "user";
		} else {
			model.addAttribute("listByDateAndSeat", ListByDateAndSeat);
			// reserveIdsを空のリストとして追加する
			model.addAttribute("reserveIds", new ArrayList<Integer>());

			return "delete/deleteConfirmation.html";
		}
	}

	@PostMapping("/deleteCompletion")
	@Transactional
	public String deleteReservation(@RequestParam List<Integer> reserveIds) {
		for (Integer reserveId : reserveIds) {
			reservationService.deleteByReserveId(reserveId);
		}
		return "delete/deleteCompletion.html";
	}

}
