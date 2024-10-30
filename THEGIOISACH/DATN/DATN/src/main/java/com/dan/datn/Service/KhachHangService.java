package com.dan.datn.Service;


import com.dan.datn.Entity.KhachHang;

import java.util.List;

public interface KhachHangService {
    List<KhachHang> layTatCaKhachHang();
    KhachHang layKhachHangTheoId(Long idKhachHang);
}
