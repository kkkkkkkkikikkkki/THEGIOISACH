package com.dan.datn.Repository;

import com.dan.datn.Entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SanPhamRepository extends JpaRepository<SanPham, String> {
    SanPham findByIdSanPham(String id);
}
