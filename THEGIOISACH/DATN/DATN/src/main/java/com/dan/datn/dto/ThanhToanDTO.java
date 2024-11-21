package com.dan.datn.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ThanhToanDTO {
    Long ID_thanh_toan;
    String phuong_thuc_thanh_toan;
    String tinh_tp;
    String Quan_huyen;
    String Phuong_xa;
    String soDienThoai;
    String diaChiChiTiet;
    double tongTien;
    List<ProductDTO> products;
}
