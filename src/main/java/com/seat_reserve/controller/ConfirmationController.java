package com.seat_reserve.controller;

import java.time.LocalDate;

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

	/*
	member.htmlから、各情報を渡しながらconfirmation.htmlに遷移する。
	*/
	@PostMapping("/confirmation")
	public String output2(@RequestParam String reserve_data,Model model) {
//		model.addAttribute("reserve_data",reserve_data);
        System.out.println(reserve_data);
        if(reserve_data != null && !reserve_data.isEmpty()){// モデルに各値を追加
        	String[] parts = reserve_data.split("_");
        	LocalDate date = LocalDate.parse(parts[0]); 
        	Integer seat = Integer.parseInt(parts[1]);
        	Integer member = Integer.parseInt(parts[2]);
        model.addAttribute("date", date);
        model.addAttribute("seat", seat);
        model.addAttribute("member", member);
		
		return "confirmation";
        }else {
        	return"redirect:/memberDetail";
        }
	}
	
	
//	@PostMapping("/completion")
//    public String completeReservation(@RequestParam String date,
//                                      @RequestParam String seat,
//                                      @RequestParam String member,
//                                      Model model) {
//        // ここでDBへの登録処理を行う
//        // 例えば、サービスクラスを使用して登録処理を行う
//        
//        // 登録が完了した後、完了画面にリダイレクトする
//        return "redirect:/complete";
//    }
}
