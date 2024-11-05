package com.dan.datn.Service;

import com.dan.datn.Entity.HoaDon;

import java.util.List;

public interface HoaDonService {
    HoaDon createHoaDon(HoaDon hoaDon);
    HoaDon updateHoaDon(Integer id, HoaDon hoaDon);
    void deleteHoaDon(Integer id);
    HoaDon getHoaDonById(Integer id);
    List<HoaDon> getAllHoaDons();
}
