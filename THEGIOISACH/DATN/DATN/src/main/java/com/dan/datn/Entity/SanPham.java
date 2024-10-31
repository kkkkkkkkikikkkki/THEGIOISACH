package com.dan.datn.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "San_Pham")
public class SanPham {

    @Id
    @Column(name = "ID_san_pham", nullable = false, length = 50)
    private String idSanPham;

    @Column(name = "ID_the_loai", length = 50)
    private String idTheLoai;

    @Column(name = "Nha_xuat_ban", length = 50)
    private String nhaXuatBan;

    @Column(name = "NSX")
    private Date nsx;

    @Column(name = "Ten_sach", length = 50)
    private String tenSach;

    @Column(name = "Tac_gia", length = 50)
    private String tacGia;

    @Column(name = "Hinh")
    private String hinh;

    @Column(name = "Gia")
    private int gia;

    @Column(name = "So_luong_da_ban")
    private int soLuongDaBan;

    @Column(name = "So_luong_ton_kho")
    private int soLuongTonKho;

    @Column(name = "So_luong_tong_san_pham")
    private int soLuongTongSanPham;

    // Getters and Setters

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(String idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public Date getNsx() {
        return nsx;
    }

    public void setNsx(Date nsx) {
        this.nsx = nsx;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoLuongDaBan() {
        return soLuongDaBan;
    }

    public void setSoLuongDaBan(int soLuongDaBan) {
        this.soLuongDaBan = soLuongDaBan;
    }

    public int getSoLuongTonKho() {
        return soLuongTonKho;
    }

    public void setSoLuongTonKho(int soLuongTonKho) {
        this.soLuongTonKho = soLuongTonKho;
    }

    public int getSoLuongTongSanPham() {
        return soLuongTongSanPham;
    }

    public void setSoLuongTongSanPham(int soLuongTongSanPham) {
        this.soLuongTongSanPham = soLuongTongSanPham;
    }
}

