package com.dan.datn.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Hoa_Don_Chi_Tiet")
@Getter
@Setter
public class HoaDonChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_HDCT;

    // Quan hệ với bảng Thanh_Toan
    @ManyToOne
    @JoinColumn(name = "ID_thanh_toan", nullable = false)
    private ThanhToan thanhToan;

    // Quan hệ với bảng Admins
    @ManyToOne
    @JoinColumn(name = "ID_nguoi_dung", nullable = false)
    private User user;
}