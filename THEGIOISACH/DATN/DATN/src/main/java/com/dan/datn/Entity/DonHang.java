package com.dan.datn.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_DonHang;

    @ManyToOne
    @JoinColumn(name = "ID_NguoiBan")
    private NguoiBan nguoiBan;

    @ManyToOne
    @JoinColumn(name = "ID_KhachHang")
    private KhachHang khachHang;

    private String TenKhachHang;
    private String DiaChiKhachHang;
    private Integer SDTKhachHang;
    private String TenSach;
    private Integer SoLuong;
    private Integer Gia;
    private Date NgayDat;
    private Integer TongTien;
    private String TrangThaiThanhToan;

    @OneToMany(mappedBy = "donHang")
    private List<HoaDonChiTiet> hoaDonChiTiets;

    @OneToMany(mappedBy = "donHang")
    private List<ThongKe> thongKes;
}

