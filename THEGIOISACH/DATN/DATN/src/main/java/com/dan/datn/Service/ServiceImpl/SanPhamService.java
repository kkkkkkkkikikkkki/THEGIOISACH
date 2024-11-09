package com.dan.datn.Service;

import com.dan.datn.Entity.SanPham;
import com.dan.datn.Repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamService {

    public static SanPhamRepository sanPhamRepository;

    @Autowired
    public SanPhamService(SanPhamRepository sanPhamRepository) {
        this.sanPhamRepository = sanPhamRepository;
    }

    public static List<SanPham> getAllSanPham() {
        return sanPhamRepository.findAll();
    }

    public List<SanPham> getSanPhamByIdRange() {
        return sanPhamRepository.findSanPhamByIdRange();
    }
}