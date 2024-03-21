package com.seat_reserve.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.seat_reserve.entity.Reservation;
import com.seat_reserve.service.ReservationService;

@Controller
public class ConfirmationController {

	//	@GetMapping("/confirmation")
	//	public String displayConfirmation() {
	//	
	//		return "confirmation";
	//		
	//	}

	@Autowired
	ReservationService reservationService;
	

	/*
	user.htmlから、各情報を渡しながらconfirmation.htmlに遷移する。
	*/
	@PostMapping("/reservation-confirmed")
	//user.htmlからreserve_dataがPOSTで送られてくる
	public String getReserveData(@RequestParam String reserve_data, Model model) {

		//予約情報が空じゃないか確認
		
		if (reserve_data != null && !reserve_data.isEmpty()) {
			//"_"で日付、座席番号、userID、user名が分かれているので、1つずつ分割し、配列にいれる
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
						List<Reservation> ListByDateAndUser = reservationService.findByDateAndUser(date, user);

						//duplicationErrorか、confirmationかの分岐
						if (ListByDateAndUser == null || ListByDateAndUser.isEmpty()) {
							
							return "reservation-confirmed";
						} else {
							return "duplicateError";
						}
					} else {
						//userをuserDetailに渡して、そのパラメータをもとに、reservationsテーブルを検索して、userDetail.htmlで表示したい。
						
						List<Reservation>ListReservationsByUser=reservationService.findByUser(user);
						model.addAttribute("listReservationsByUser",ListReservationsByUser);
						//dateとseatがNullなら詳細画面に遷移
						return "user-detail";
					}
				} catch (NumberFormatException | DateTimeParseException e) {
					//数値や日付の解析に失敗した場合はエラー処理を行う
					e.printStackTrace();

				}
			}
		} else {
			return "redirect:/seat";
			//なにもなかったら、userDetailに飛ばしたい。
		}
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
