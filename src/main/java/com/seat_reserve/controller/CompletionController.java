package com.seat_reserve.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.seat_reserve.service.ReservationService;

@Controller
public class CompletionController {

	@Autowired
	ReservationService reservationService;

	//以下で、DBにインサートする
	@PostMapping("/reservation-completed")
	public String completeReservation(
			@RequestParam LocalDate date,
			@RequestParam Integer seat,
			@RequestParam Integer user,
			Model model) {

		reservationService.completeReservation(date, seat, user);

		return "reservation-completed";
	}
}
