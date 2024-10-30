package com.dan.datn.Entity;

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
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_KhachHang;

    @ManyToOne
    @JoinColumn(name = "ID_admin")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "ID_SanPham")
    private SanPham sanPham;

    private String Ten;
    private String Email;
    private Integer SDT;
    private Date NgayDangKi;
    private String DiaChi;

    @OneToMany(mappedBy = "khachHang")
    private List<DonHang> donHangs;

    @OneToMany(mappedBy = "khachHang")
    private List<DanhGia> danhGias;
}

