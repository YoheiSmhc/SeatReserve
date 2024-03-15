package com.seat_reserve.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.seat_reserve.service.ReserveService;

@Controller
public class CompletionController {
	
	
	@Autowired
	ReserveService reserveService;
	
	@GetMapping("/completion")
	public String displayCompletion() {
	
		return "completion";
		
	}
	
	
	
	//以下で、DBにインサートする
	@PostMapping("/completion")
	public String postCompletion(
			@RequestParam LocalDate date,
            @RequestParam Integer seat,
            @RequestParam Integer user,
            Model model) {
           
		reserveService.completeReservation(date, seat, user);
            	
		return "completion";
	}
}
