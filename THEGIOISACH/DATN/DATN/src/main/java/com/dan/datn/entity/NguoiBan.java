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
public class NguoiBan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_NguoiBan;

    @ManyToOne
    @JoinColumn(name = "ID_admin")
    private Admin admin;

    private String Ten;
    private String Email;
    private String DiaChi;
    private Integer SDT;
    private String MatKhau;
    private Date NgayThamGia;

    @OneToMany(mappedBy = "nguoiBan")
    private List<SanPham> sanPhams;

    @OneToMany(mappedBy = "nguoiBan")
    private List<DonHang> donHangs;

    @OneToMany(mappedBy = "nguoiBan")
    private List<ThongKe> thongKes;
}

