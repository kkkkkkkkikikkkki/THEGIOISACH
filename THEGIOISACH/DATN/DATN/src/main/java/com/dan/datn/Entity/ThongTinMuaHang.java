package com.dan.datn.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "thong_tin_mua_hang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThongTinMuaHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String tinh_tp;
    String Quan_huyen;
    String Phuong_xa;
    String soDienThoai;
    String diaChiChiTiet;
}
