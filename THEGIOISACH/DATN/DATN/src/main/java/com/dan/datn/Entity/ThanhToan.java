package com.dan.datn.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "thanh_toans")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_thanh_toan;
    private String phuongThucThanhToan;
    private  double tongTien;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "thanh_toan_id")
    private List<SanPham> sanPhams;
    @OneToOne(cascade = CascadeType.ALL)
    private ThongTinMuaHang thongTinMuaHang;
    @OneToMany(mappedBy = "thanhToan", cascade = CascadeType.ALL)
    private List<HoaDonChiTiet> hoaDonChiTiets;
}
