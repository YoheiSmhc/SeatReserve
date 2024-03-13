package com.seat_reserve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConfirmationController {
	
	@GetMapping("/confirmation")
	public String displayConfirmation() {
	
		return "confirmation";
		
	}
	
	@PostMapping("/confirmation")
	public String output2(@RequestParam String reserve_info_2,Model model) {
		model.addAttribute("reserve_info_2",reserve_info_2);
		return "confirmation";
	}
}
