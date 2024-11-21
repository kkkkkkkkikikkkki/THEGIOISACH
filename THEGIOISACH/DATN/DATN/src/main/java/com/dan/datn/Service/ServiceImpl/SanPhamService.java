package com.dan.datn.Service.ServiceImpl;

import com.dan.datn.Entity.SanPham;
import com.dan.datn.Repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    // Lấy sản phẩm theo danh sách ID cụ thể
    public List<SanPham> getSanPhamBySpecificIds() {
        return sanPhamRepository.findSanPhamBySpecificIds();
    }

    public List<SanPham> getSanPhamSanPham(int limit) {
        List<SanPham> allSanPhams = sanPhamRepository.findAll();
        Collections.shuffle(allSanPhams);
        return allSanPhams.subList(0, Math.min(limit, allSanPhams.size()));
    }

    // Phương thức lấy sản phẩm theo ID
    public SanPham getSanPhamById(Long id) {
        Optional<SanPham> sanPham = sanPhamRepository.findById(id);
        if (sanPham.isPresent()) {
            return sanPham.get();
        } else {
            throw new IllegalArgumentException("Sản phẩm không tồn tại với ID: " + id);
        }
    }

    public List<SanPham> searchAllFields(String keyword) {
        List<SanPham> sanPhams = sanPhamRepository.searchAllFields(keyword);

        // Chuyển đổi hình ảnh sang Base64
        for (SanPham sp : sanPhams) {
            if (sp.getHinh() != null && sp.getHinh().getHinhMain() != null) {
                sp.getHinh().setBase64Image(sp.getHinh().getBase64MainImage());
            }
        }

        return sanPhams;
    }

    public List<String> findSuggestions(String keyword) {
        return sanPhamRepository.findSuggestionsByKeyword(keyword);
    }

    public List<SanPham> findByTheLoai(Long theLoaiId, Long excludeId) {
        return sanPhamRepository.findByTheLoaiId(theLoaiId, excludeId);
    }

    public List<SanPham> getRandomProducts() {
        return sanPhamRepository.findRandomProducts();
    }

}