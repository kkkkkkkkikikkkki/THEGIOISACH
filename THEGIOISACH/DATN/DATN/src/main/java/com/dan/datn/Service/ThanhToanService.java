package com.dan.datn.Service;

import com.dan.datn.Entity.HoaDonChiTiet;
import com.dan.datn.Entity.ThanhToan;
import com.dan.datn.dto.ThanhToanDTO;

import java.util.List;

public interface ThanhToanService {

    ThanhToan createThanhToan(ThanhToanDTO request);
    List<HoaDonChiTiet> getAllThanhToans();

}
