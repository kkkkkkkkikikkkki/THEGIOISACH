package com.dan.datn.Service;

import com.dan.datn.Entity.HoaDonChiTiet;

import java.util.List;

public interface HoaDonChiTietService {
    HoaDonChiTiet createHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet);
    HoaDonChiTiet updateHoaDonChiTiet(Integer id, HoaDonChiTiet hoaDonChiTiet);
    void deleteHoaDonChiTiet(Integer id);
    HoaDonChiTiet getHoaDonChiTietById(Integer id);
    List<HoaDonChiTiet> getAllHoaDonChiTiets();
    // In HoaDonChiTietService.java

}