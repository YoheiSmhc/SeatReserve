package com.seat_reserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.seat_reserve.entity.Member;
import com.seat_reserve.entity.Reserve;
import com.seat_reserve.service.MemberService;
import com.seat_reserve.service.ReserveService;


@Controller
public class ReserveController {
	
	@Autowired
	ReserveService reserveService;
	
	@Autowired
	MemberService memberService;

	/*
	社員の詳細情報
	*/
	@GetMapping("/member/{userId}")
	public String displayMemberDetail(@PathVariable Integer userId,Model model) {
		/*
		Idに合致した情報をmemberData変数に格納する
		*/ 
		Member memberData = memberService.findById(userId);
		/*
		memberData変数を、HTMLでも使えるようにするために、モデルのmemberDataオブジェクトに変換する？
		*/
		model.addAttribute("memberData",memberData);
		
		 List<Reserve> reserveData = reserveService.findByUserId(userId);
	        model.addAttribute("reserveData", reserveData);

		return "memberDetail";
		
	}
	/*
	社員の予約詳細情報
	*/
//	@GetMapping("/member/{userId}")
//	public String displayReserveDetail(@PathVariable Integer userId,Model model) {
//		/*
//		Idに合致した情報をmemberData変数に格納する
//		*/ 
//		List<Reserve> reserveData = reserveService.findByUserId(userId);
//		/*
//		memberData変数を、HTMLでも使えるようにするために、モデルのmemberDataオブジェクトに変換する？
//		*/
//		model.addAttribute("reserveData",reserveData);
//		return "memberDetail";
//		
//	}
}
