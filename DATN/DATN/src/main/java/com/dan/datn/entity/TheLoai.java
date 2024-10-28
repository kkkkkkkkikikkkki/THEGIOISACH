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
public class TheLoai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_TheLoai;

    private String TheLoai;
    private String TenTheLoai;

    @OneToMany(mappedBy = "theLoai")
    private List<SanPham> sanPhams;
}

