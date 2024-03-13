package com.seat_reserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.seat_reserve.entity.Member;
import com.seat_reserve.service.MemberService;


@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
//	member.htmlに、メンバー一覧情報を渡す
	@GetMapping("/member")
	public String displayMember(Model model) {
		List<Member>memberlist=memberService.seachAll();
		model.addAttribute("memberlist",memberlist);
		return "member";
		
	}
	@PostMapping("/member")
	public String output1(@RequestParam String reserve_info_1,Model model) {
		//Seat.htmlでpostされた情報をreserve_info_1に格納
		model.addAttribute("reserve_info_1",reserve_info_1);
		//メンバー一覧を取得
		List<Member>memberlist=memberService.seachAll();
		model.addAttribute("memberlist",memberlist);
		//member.htmlを表示
		return "member";
	}
	

}
