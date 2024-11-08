//package com.dan.datn.Service.ServiceImpl;
//
//import com.dan.datn.Entity.SanPham;
//import com.dan.datn.Repository.SanPhamRepository;
//import com.dan.datn.Service.SanPhamService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class SanPhamServiceImpl implements SanPhamService {
//
//    @Autowired
//    private SanPhamRepository sanPhamRepository;
//
//    @Override
//    public List<SanPham> getAllSanPhams() {
//        return sanPhamRepository.findAll();
//    }
//
//    @Override
//    public Optional<SanPham> getSanPhamById(String id) {
//        return sanPhamRepository.findById(id);
//    }
//
//    @Override
//    public SanPham saveSanPham(SanPham sanPham) {
//        return sanPhamRepository.save(sanPham);
//    }
//
//    @Override
//    public void deleteSanPham(String id) {
//        sanPhamRepository.deleteById(id);
//    }
//}
