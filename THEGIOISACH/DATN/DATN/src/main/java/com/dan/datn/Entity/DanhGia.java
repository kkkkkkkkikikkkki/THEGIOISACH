package com.dan.datn.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DanhGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_DanhGia;

    @ManyToOne
    @JoinColumn(name = "ID_KhachHang")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "ID_SanPham")
    private SanPham sanPham;

    private String BinhLuan;
    private Integer DanhGia;
    private Date NgayDanhGia;
}
