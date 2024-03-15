package com.seat_reserve.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    
    //こいつを紐づける
    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "seat_id")
    private Integer seatId;
    
    @Column(name = "reserve_date")
    private LocalDate reserveDate;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
//    @Transient
//    private String name;
    
//    @ManyToOne(fetch = FetchType.LAZY,cascade=XX)
//    @JoinColumn(name = "id", referencedColumnName = "user_id")
//    private Member member;
    
    @PrePersist
    public void prePersist() {
        // 現在時刻を取得してcreated_atに設定
        this.createdAt = LocalDateTime.now();

    }

	
    
}


