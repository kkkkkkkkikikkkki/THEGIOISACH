package com.dan.datn.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Hinh")
@Getter
@Setter
public class Hinh {

    @Id
    private Long ID_hinh;

    @Column(name = "Hinh_main", nullable = false)
    private byte[] hinhMain;

    @Column(name = "Hinh_1", nullable = false)
    private byte[] hinh1;

    @Column(name = "Hinh_2", nullable = false)
    private byte[] hinh2;

    @Column(name = "Hinh_3", nullable = false)
    private byte[] hinh3;

    @Column(name = "Hinh_4", nullable = false)
    private byte[] hinh4;

    // Relationships
    @OneToOne(mappedBy = "hinh")
    private SanPham sanPham;
}
