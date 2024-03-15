package com.seat_reserve.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.seat_reserve.entity.Member;
import com.seat_reserve.entity.Reserve;
import com.seat_reserve.service.MemberService;
import com.seat_reserve.service.ReserveService;


@Controller
public class ReserveController {
	
	@Autowired
	ReserveService reserveService;
	
	@Autowired
	MemberService memberService;

	/*
	社員の詳細情報
	*/
	@GetMapping("/member/{userId}")
	public String displayMemberDetail(@PathVariable Integer userId,Model model) {
		/*
		Idに合致した情報をmemberData変数に格納する
		*/ 
		Member memberData = memberService.findById(userId);
		/*
		memberData変数を、HTMLでも使えるようにするために、モデルのmemberDataオブジェクトに変換する？
		*/
		model.addAttribute("memberData",memberData);
		
		/*
		予約情報をreserveDataに格納 
		*/
		List<Reserve> reserveData = reserveService.findByUserId(userId);
		model.addAttribute("reserveData", reserveData);
			
//		memberDetail.htmlを返す
		return "memberDetail";
		
	}
	
	// 指定された日付に基づいて予約情報を取得し、JSON形式で返す
    @GetMapping("/reservations")
    public ResponseEntity<List<Reserve>> getReservationsByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<Reserve> reservations = reserveService.findByDate(date);
//       
//        for (Reserve reservation: reservations) {
//        	// 予約に関連するユーザーのIDを取得
//            Integer userId = reservation.getUserId();
//            // ユーザーIDを使用してユーザー名を取得
//            Member member = memberService.findById(userId);
//           
//            // 取得したユーザー名を予約に設定
//            reservation.getName().setName(member.getName());
//        }
//            
        
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
	
}
