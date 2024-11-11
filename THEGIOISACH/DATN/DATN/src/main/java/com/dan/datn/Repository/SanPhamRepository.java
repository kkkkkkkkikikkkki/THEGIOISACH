package com.dan.datn.Repository;

import com.dan.datn.Entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.dan.datn.Service.SanPhamService.sanPhamRepository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    @Query("SELECT s FROM SanPham s WHERE s.ID_san_pham BETWEEN 3 AND 6")
    List<SanPham> findSanPhamByIdRange();

    public default List<SanPham> getSanPhamByIdRange() {
        return sanPhamRepository.findSanPhamByIdRange();
    }

    // Phương thức tìm sản phẩm theo ID
    Optional<SanPham> findById(Long id);

    @Query("SELECT s FROM SanPham s " +
            "JOIN FETCH s.hinh h " +
            "JOIN FETCH s.theLoai tl " +
            "WHERE LOWER(s.tenSach) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(s.nhaXuatBan) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(s.tacGia) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(s.moTa) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(tl.theLoai) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<SanPham> searchAllFields(String keyword);

    @Query("SELECT s.tenSach FROM SanPham s WHERE LOWER(s.tenSach) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<String> findSuggestionsByKeyword(@Param("keyword") String keyword);


}
