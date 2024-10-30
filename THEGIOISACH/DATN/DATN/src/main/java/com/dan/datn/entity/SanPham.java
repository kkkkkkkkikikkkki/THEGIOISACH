package com.dan.datn.Entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_SanPham;

    @ManyToOne
    @JoinColumn(name = "ID_TheLoai")
    private TheLoai theLoai;

    @ManyToOne
    @JoinColumn(name = "ID_NguoiBan")
    private NguoiBan nguoiBan;

    private String TenNguoiBan; // Đổi tên trường này
    private String TenSach;
    private String TacGia;
    private String NhaXuatBan;
    private String NSX;
    private Integer Gia;

    @Lob
    private byte[] Hinh;

    private String SoLuongDaBan;
    private Integer SoLuongTongSanPham;
    private String SoLuongTonKho;

    @OneToMany(mappedBy = "sanPham")
    private List<ThongKe> thongKes;

    @OneToMany(mappedBy = "sanPham")
    private List<DanhGia> danhGias;
}
