package com.dan.datn.Entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ThongKe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_ThongKe;

    @ManyToOne
    @JoinColumn(name = "ID_DonHang")
    private DonHang donHang;

    @ManyToOne
    @JoinColumn(name = "ID_SanPham")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "ID_NguoiBan")
    private NguoiBan nguoiBan;

    private Integer Gia;
    private Integer SoLuong;
}

