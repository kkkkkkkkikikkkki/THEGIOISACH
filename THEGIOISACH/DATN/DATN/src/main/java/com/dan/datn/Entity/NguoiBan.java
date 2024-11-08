package com.dan.datn.Entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "NguoiBan")
public class NguoiBan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NguoiBan")
    private Integer idNguoiBan;

    @Column(name = "ID_admin")
    private Integer idAdmin;

    @Column(name = "Ten", nullable = false, length = 50)
    private String ten;

    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Column(name = "DiaChi", nullable = false, length = 50)
    private String diaChi;

    @Column(name = "SĐT", nullable = false)
    private Integer sdt;

    @Column(name = "MatKhau", nullable = false, length = 50)
    private String matKhau;

    @Column(name = "NgayThamGia", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ngayThamGia;

    // Getters and Setters
    public Integer getIdNguoiBan() {
        return idNguoiBan;
    }

    public void setIdNguoiBan(Integer idNguoiBan) {
        this.idNguoiBan = idNguoiBan;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Integer getSdt() {
        return sdt;
    }

    public void setSdt(Integer sdt) {
        this.sdt = sdt;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public Date getNgayThamGia() {
        return ngayThamGia;
    }

    public void setNgayThamGia(Date ngayThamGia) {
        this.ngayThamGia = ngayThamGia;
    }
}
