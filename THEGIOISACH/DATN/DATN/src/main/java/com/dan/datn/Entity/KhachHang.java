package com.dan.datn.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Khach_Hang") // Đảm bảo tên bảng chính xác
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Khach_Hang") // Đảm bảo tên cột chính xác
    private Long idKhachHang;

    @Column(name = "ID_admin")
    private Long idAdmin;

    @Column(name = "ID_San_Pham")
    private Long idSanPham;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "Email")
    private String email;

    // Đổi tên cột nếu có thể thành "SDT" để tránh ký tự đặc biệt
    @Column(name = "SĐT")
    private int sdt;

    @Column(name = "Ngay_Dang_Ki")
    private LocalDate ngayDangKi;

    @Column(name = "Dia_Chi")
    private String diaChi;

    // Getters và Setters
    public Long getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Long idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public Long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Long getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(Long idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public LocalDate getNgayDangKi() {
        return ngayDangKi;
    }

    public void setNgayDangKi(LocalDate ngayDangKi) {
        this.ngayDangKi = ngayDangKi;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
