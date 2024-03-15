package com.seat_reserve.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seat_reserve.entity.Reserve;
import com.seat_reserve.repository.ReserveRepository;

@Service
public class ReserveService {

	@Autowired
	ReserveRepository reserveRepository;

	/*
	ユーザー情報を全検索
	*/
	public List<Reserve>seachAll(){
		return  (List<Reserve>) reserveRepository.findAll();
		
	}
	
//	選択した情報をDBに登録。setterで登録する。
	 public void completeReservation(LocalDate date, Integer seat, Integer user) {
//	        Reserveレポジトリをインスタンス化
	        Reserve reserve = new Reserve();
	        reserve.setReserveDate(date);
	        reserve.setSeatId(seat);
	        reserve.setUserId(user);
	        LocalDateTime now = LocalDateTime.now();
	        reserve.setCreatedAt(now);
	        
	        reserveRepository.save(reserve);
	    }
	 
	  // 指定された日付に基づいて予約情報を取得するメソッド
	    public List<Reserve> findByDate(LocalDate date) {
	        return reserveRepository.findByReserveDate(date);
	    }
	/*
	ユーザー情報をIdで検索
	*/
//	public Reserve findById(Integer userId) {
//        return reserveRepository.findById(userId).get();
//    }
	
//	@Autowired
//    private JdbcTemplate jdbcTemplate; // Spring JDBCのJdbcTemplateを使う
//
//    public List<Reserve> getReservationsByMemberId(Long userId) {
//        String sql = "SELECT reserveId FROM reserve WHERE userId = ?";
//        return jdbcTemplate.query(sql, new Object[]{userId}, new ResultSetExtractor<List<Reserve>>() {
//            @Override
//            public List<Reserve> extractData(ResultSet rs) throws SQLException, DataAccessException {
//                List<Reserve> reservations = new ArrayList<>();
//                while (rs.next()) {
//                    Long reserveId = rs.getLong("reserveId");
//                    // ここで適切なReservationオブジェクトを作成してリストに追加する
//                    Reserve Reserve = new Reserve(reserveId);
//                    reserve.add(Reserve);
//                }
//                return reserve;
//            }});
//    }
	
//	 社員IDで検索。
	public List<Reserve> findByUserId(Integer userId) {
        return reserveRepository.findByUserId(userId);
    }
	
	
//    
	
}
