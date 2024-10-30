package com.dan.datn.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_admin;

    private String Ten;
    private String Email;
    private String MatKhau;

    @OneToMany(mappedBy = "admin")
    private List<KhachHang> khachHangs;

    @OneToMany(mappedBy = "admin")
    private List<NguoiBan> nguoiBans;
}

