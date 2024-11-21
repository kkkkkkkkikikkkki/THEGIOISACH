package com.dan.datn.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Nguoi_Dung")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_nguoi_dung;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "SDT")
    private Integer SDT;

    @Column(name = "Dia_chi")
    private String Dia_chi;

    @Column(name = "Mat_khau")
    private String Mat_khau;

    @Column(name = "Email")
    private String email;

    @Column(name = "Role", nullable = false)
    private int role = 1; // Default role set to 1

    // Relationships
    @OneToMany(mappedBy = "user")
    private List<DanhGia> danhGiaList;

    @OneToMany(mappedBy = "user")  // Change 'admins' to 'user'
    private List<ThanhToan> thanhToanList;


}