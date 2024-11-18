package com.dan.datn.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Thong_Ke")
@Getter
@Setter
public class ThongKe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_thong_ke;

    @Column(name = "So_luong", nullable = false)
    private Integer soLuong;

    @Column(name = "Thoi_gian", nullable = false)
    private Date thoiGian;

    @Column(name = "Doanh_thu", nullable = false)
    private Integer doanhThu;

    @Column(name = "Gia", nullable = false)
    private Integer gia;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "ID_don_hang", nullable = false)
    private ThanhToan thanhToan;

    @ManyToOne
    @JoinColumn(name = "ID_san_pham", nullable = false)
    private SanPham sanPham;
}

