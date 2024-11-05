package com.dan.datn.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Entity
@Table(name = "San_Pham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_san_pham")
    private int idSanPham;

    @ManyToOne
    @JoinColumn(name = "ID_the_loai", nullable = false)
    private TheLoai theLoai; // Mapping to TheLoai

    @Column(name = "Nha_xuat_ban", nullable = false, length = 50)
    private String nhaXuatBan;

    @Column(name = "NSX", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date nsx;

    @Column(name = "Ten_sach", nullable = false, length = 50)
    private String tenSach;

    @Column(name = "Tac_gia", nullable = false, length = 50)
    private String tacGia;

    @Lob
    @Column(name = "Hinh", nullable = false)
    private byte[] hinh; // Changed IMAGE to byte[] for image storage

    @Column(name = "Gia", nullable = false)
    private int gia;

    @Column(name = "So_luong_da_ban")
    private int soLuongDaBan;

    @Column(name = "So_luong_ton_kho")
    private int soLuongTonKho;

    @Column(name = "So_luong_tong_san_pham")
    private int soLuongTongSanPham;
}
