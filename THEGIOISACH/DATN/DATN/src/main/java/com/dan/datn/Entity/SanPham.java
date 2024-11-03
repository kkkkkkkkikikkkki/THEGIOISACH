package com.dan.datn.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "San_Pham")
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_san_pham")
    private Long idSanPham;

    @Column(name = "ID_the_loai")
    private Long idTheLoai;

    @Column(name = "Nha_xuat_ban")
    private String nhaXuatBan;

    @Column(name = "NSX")
    private String nsx;

    @Column(name = "Ten_sach")
    private String tenSach;

    @Column(name = "Tac_gia")
    private String tacGia;

    @Column(name = "Hinh")
    private String hinh;

    @Column(name = "Gia")
    private Double gia;

    @Column(name = "So_luong_da_ban")
    private Integer soLuongDaBan;

    @Column(name = "So_luong_ton_kho")
    private Integer soLuongTonKho;

    @Column(name = "So_luong_tong_san_pham")
    private Integer soLuongTongSanPham;

    // Getters and Setters
}