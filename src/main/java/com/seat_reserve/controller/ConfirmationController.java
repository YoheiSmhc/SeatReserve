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
	public String output2(@RequestParam String reserve_data,Model model) {
//		model.addAttribute("reserve_data",reserve_data);
		String[] parts = reserve_data.split("_");
        String date = parts[0];
        Integer seat = Integer.parseInt(parts[1]);
        Integer member = Integer.parseInt(parts[2]);
        
        // モデルに各値を追加
        model.addAttribute("date", date);
        model.addAttribute("seat", seat);
        model.addAttribute("member", member);
		
		return "confirmation";
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
