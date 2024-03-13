package com.seat_reserve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CompletionController {
	
	@GetMapping("/completion")
	public String displayCompletion() {
	
		return "completion";
		
	}
	
	//以下で、DBにインサートする感じ？
	@PostMapping("/completion")
	public String postCompetion() {
		return "completion";
	}
}
