package com.dan.datn.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Hoa_Don")
@Getter
@Setter
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_hoa_don;

    @Column(name = "Ngay_dat_hang", nullable = false)
    private Date ngayDatHang;

    @Column(name = "Tong_tien", nullable = false)
    private Integer tongTien;

    @Column(name = "Trang_thai_thanh_toan", nullable = false)
    private String trangThaiThanhToan;

    @Column(name = "SDT", nullable = false)
    private Integer SDT;

    @Column(name = "Ten", nullable = false)
    private String ten;

    @Column(name = "Dia_chi", nullable = false)
    private String diaChi;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "ID_nguoi_dung", nullable = false)
    private User admins;

    @OneToMany(mappedBy = "hoaDon")
    private List<HoaDonChiTiet> hoaDonChiTietList;
}



