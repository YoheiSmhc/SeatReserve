package com.seat_reserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.seat_reserve.entity.User;
import com.seat_reserve.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
//	member.htmlに、メンバー一覧情報を渡す
	@GetMapping("/user")
	public String displayUser(@RequestParam(name="seat",required=false)String seat,
								@RequestParam(name="date",required=false)String date,
								Model model) {
		if(seat!=null&&date!=null) {
		model.addAttribute("seat",seat);
		model.addAttribute("date",date);
	}else {}
//		メンバー一覧を取得し、memberlistオブジェクトに格納
		List<User>userlist=userService.seachAll();
		model.addAttribute("userlist",userlist);
		return "user";
		
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
	

}
