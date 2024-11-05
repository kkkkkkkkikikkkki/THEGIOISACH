package com.dan.datn.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "The_Loai")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheLoai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_the_loai")
    private int idTheLoai;

    @Column(name = "The_loai", nullable = false, length = 50)
    private String theLoai;

    @OneToMany(mappedBy = "theLoai", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SanPham> sanPhams; // Changed variable name to plural for clarity

}
