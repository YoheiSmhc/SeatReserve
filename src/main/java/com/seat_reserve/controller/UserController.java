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

import com.seat_reserve.entity.Reserve;
import com.seat_reserve.entity.User;
import com.seat_reserve.service.ReserveService;
import com.seat_reserve.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	ReserveService reserveService;

	//	user.htmlに、メンバー一覧情報を渡す
	@GetMapping("/user")
	public String displayUser(
			@RequestParam(name = "seat", required = false) Integer seat,
			@RequestParam(name = "date", required = false) LocalDate date,
			Model model) {
		//seatIdとreserveDateが渡されたら、それぞれを渡して、予約機能に移る

		//userService.findBySeatAndDate(seat,date);

		//既にseatとdateが入ってたら、削除機能に遷移
		List<Reserve> ListByDateAndSeat = reserveService.findByDateAndSeat(date, seat);
		System.out.println("ListByDateAndSeatです："+ListByDateAndSeat);
		//seatとdateがあったら削除機能、なければuser.htmlを介して予約機能
		if (ListByDateAndSeat == null || ListByDateAndSeat.isEmpty()) {
			model.addAttribute("seat", seat);
			model.addAttribute("date", date);
			//メンバー一覧を取得し、userlistオブジェクトに格納
			List<User> userlist = userService.seachAll();
			model.addAttribute("userlist", userlist);
			System.out.println("ListByDateAndSeatです："+ListByDateAndSeat);
			return "user";
		} else {
			//拡張for文で、ListByDateAndSeatの全ての値を取得
			//			for (Reserve reserve : ListByDateAndSeat) {
			//				Integer reserveId = reserve.getReserveId();
			//				System.out.println("ReserveIDです：" + reserveId);
			//
			//				//reserveIdで削除を実行
			//
			//			}
			//	
			System.out.println("ListByDateAndSeatです："+ListByDateAndSeat);

			model.addAttribute("listByDateAndSeat", ListByDateAndSeat);
			// reserveIdsを空のリストとして追加する
			model.addAttribute("reserveIds", new ArrayList<Integer>());

			return "delete/deleteConfirmation.html";
		}
	}

	@PostMapping("deleteCompletion")
	@Transactional
	public String deleteReservation(@RequestParam List<Integer> reserveIds) {
		for (Integer reserveId : reserveIds) {
			reserveService.deleteByReserveId(reserveId);
		}
		return "delete/deleteCompletion.html";
	}

}

//	@PostMapping("/member")
//	public String output1(@RequestParam String reserve_info_1,Model model) {
//		//Seat.htmlでpostされた情報をreserve_info_1に格納
//		model.addAttribute("reserve_info_1",reserve_info_1);
//		//メンバー一覧を取得
//		List<Member>memberlist=memberService.seachAll();
//		model.addAttribute("memberlist",memberlist);
//		//member.htmlを表示
//		return "member";
//	}
