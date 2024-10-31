package com.dan.datn.Service;

import com.dan.datn.Entity.SanPham;

import java.util.List;
import java.util.Optional;

public interface SanPhamService {
    List<SanPham> getAllSanPhams();
    Optional<SanPham> getSanPhamById(String id);
    SanPham saveSanPham(SanPham sanPham);
    void deleteSanPham(String id);
}
