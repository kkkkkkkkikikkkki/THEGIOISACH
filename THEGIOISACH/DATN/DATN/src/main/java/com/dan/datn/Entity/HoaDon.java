package com.dan.datn.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Hoa_Don")
@Getter
@Setter
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_hoa_don")
    private Integer id;

    @Column(name = "Ngay_dat_hang", nullable = false)
    private Date ngayDatHang;

    @Column(name = "Tong_tien", nullable = false)
    private Integer tongTien;

    @Column(name = "Trang_thai_thanh_toan", nullable = false)
    private String trangThaiThanhToan;

    @Column(name = "SDT", nullable = false)
    private Integer sdt;

    @Column(name = "Ten", nullable = false)
    private String ten;

    @Column(name = "Dia_chi", nullable = false)
    private String diaChi;

    @ManyToOne
    @JoinColumn(name = "ID_nguoi_dung", nullable = false)
    private User nguoiDung;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL)
    private List<HoaDonChiTiet> chiTietHoaDon;

    // Getters and Setters
}

