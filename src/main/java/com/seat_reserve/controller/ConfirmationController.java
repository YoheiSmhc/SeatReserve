package com.seat_reserve.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.seat_reserve.entity.Reserve;
import com.seat_reserve.service.ReserveService;
import com.seat_reserve.service.UserService;

@Controller
public class ConfirmationController {

	//	@GetMapping("/confirmation")
	//	public String displayConfirmation() {
	//	
	//		return "confirmation";
	//		
	//	}

	@Autowired
	ReserveService reserveService;
	
	@Autowired
	UserService userService;

	/*
	user.htmlから、各情報を渡しながらconfirmation.htmlに遷移する。
	*/
	@PostMapping("/confirmation")
	//user.htmlからreserve_dataがPOSTで送られてくる
	public String getReserveData(@RequestParam String reserve_data, Model model) {
		//model.addAttribute("reserve_data",reserve_data);
		//System.out.println(reserve_data);
		//予約情報が空じゃないか確認
		
		if (reserve_data != null && !reserve_data.isEmpty()) {
			String[] parts = reserve_data.split("_");
			if (parts.length == 4) {
				LocalDate date = null;
				Integer seat = null;
				try {
					date = "null".equals(parts[0]) ? null : LocalDate.parse(parts[0]);
	                seat = "null".equals(parts[1]) ? null : Integer.parseInt(parts[1]);
					Integer user = Integer.parseInt(parts[2]);
					String userName = parts[3];
					model.addAttribute("date", date);
					model.addAttribute("seat", seat);
					model.addAttribute("user", user);
					model.addAttribute("userName", userName);
					//dateとseatが2つともnullだったらuserdetailに
					if (date != null && seat != null) {
						//dateとuser_idそれぞれを元に、rerservationsテーブルを検索
						List<Reserve> ListByDateAndUser = reserveService.findByDateAndUser(date, user);

						//duplicationErrorか、confirmationかの分岐
						if (ListByDateAndUser == null || ListByDateAndUser.isEmpty()) {
							return "confirmation";
						} else {
							System.out.println(ListByDateAndUser);
							return "duplicateError";
						}
					} else {
						//userをuserDetailに渡して、そのパラメータをもとに、reservationsテーブルを検索して、userDetail.htmlで表示したい。
						
						List<Reserve>reserves=reserveService.findByUser(user);
						model.addAttribute("reserves",reserves);
						return "UserDetail";
					}
				} catch (NumberFormatException | DateTimeParseException e) {
					//数値や日付の解析に失敗した場合はエラー処理を行う
					e.printStackTrace();

				}
			}
		} else {
			System.out.println("データです" + reserve_data);
			return "redirect:/seat";
			//なにもなかったら、userDetailに飛ばしたい。
		}
		System.out.println("最後の行です" + reserve_data);
		return "redirect:/seat";
	}
}

//	@PostMapping("/completion")
//    public String completeReservation(@RequestParam String date,
//                                      @RequestParam String seat,
//                                      @RequestParam String user,
//                                      Model model) {
//        // ここでDBへの登録処理を行う
//        // 例えば、サービスクラスを使用して登録処理を行う
//        
//        // 登録が完了した後、完了画面にリダイレクトする
//        return "redirect:/complete";
//    }
