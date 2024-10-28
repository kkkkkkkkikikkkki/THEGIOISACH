package com.dan.datn.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_HoaDonChiTiet;

    @ManyToOne
    @JoinColumn(name = "ID_HoaDon")
    private DonHang hoaDon;

    @ManyToOne
    @JoinColumn(name = "ID_SanPham")
    private SanPham sanPham;

    private String TenSach;
    private Integer Gia;
    private Integer SoLuong;
}

