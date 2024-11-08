package com.dan.datn.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Hoa_Don_Chi_Tiet")
@Getter
@Setter
public class HoaDonChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_HDCT;

    @Column(name = "Ten_sach", nullable = false)
    private String tenSach;

    @Column(name = "So_luong", nullable = false)
    private Integer soLuong;

    @Column(name = "Gia", nullable = false)
    private Integer gia;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "ID_hoa_don", nullable = false)
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "ID_san_pham", nullable = false)
    private SanPham sanPham;
}

