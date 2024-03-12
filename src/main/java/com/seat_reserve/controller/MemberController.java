package com.seat_reserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	

}
