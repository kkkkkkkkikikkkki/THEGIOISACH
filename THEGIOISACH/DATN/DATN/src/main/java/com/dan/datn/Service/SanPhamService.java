package com.dan.datn.Service;

import com.dan.datn.Entity.SanPham;
import com.dan.datn.Repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamService {

    // Inject the SanPhamRepository using @Autowired
    @Autowired
    private SanPhamRepository sanPhamRepository;

    // Method to get all products
    public List<SanPham> getAllSanPham() {
        // Use the sanPhamRepository instance to call findAll()
        return sanPhamRepository.findAll();
    }
}