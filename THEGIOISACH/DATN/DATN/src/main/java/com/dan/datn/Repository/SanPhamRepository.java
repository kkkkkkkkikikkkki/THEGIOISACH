package com.dan.datn.Repository;

import com.dan.datn.Entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.dan.datn.Service.SanPhamService.sanPhamRepository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    @Query("SELECT s FROM SanPham s WHERE s.ID_san_pham BETWEEN 3 AND 6")
    List<SanPham> findSanPhamByIdRange();

    public default List<SanPham> getSanPhamByIdRange() {
        return sanPhamRepository.findSanPhamByIdRange();
    }

}
