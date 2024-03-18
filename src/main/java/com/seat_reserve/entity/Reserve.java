package com.seat_reserve.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "reservations")
public class Reserve {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reserveId;
    
    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "seat_id")
    private Integer seatId;
    
    @Column(name = "reserve_date")
    private LocalDate reserveDate;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
//    Hibernateのプロキシオブジェクトをシリアライズ可能なオブジェクトに変換。良く分からないけど、これをしないと予約情報を取ってくることができない。
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    Reserveが多、userが1なので
    @ManyToOne(fetch = FetchType.LAZY)
//    user_idは上でも指定していて、重複している状態。なので、こちらは挿入や更新をfalseにしておく。setterやgetterは上のseta_idにまかせる
    @JoinColumn(name = "user_id", referencedColumnName = "id",insertable = false, updatable = false)
	public User user;
    
    @PrePersist
    public void prePersist() {
        // 現在時刻を取得してcreated_atに設定
        this.createdAt = LocalDateTime.now();

    }

	
    
}


