package com.dan.datn.Service;

import com.dan.datn.Entity.HoaDon;

import java.util.List;

public interface HoaDonService {
    List<HoaDon> getOrderDetailsByUsername(String username);
}
