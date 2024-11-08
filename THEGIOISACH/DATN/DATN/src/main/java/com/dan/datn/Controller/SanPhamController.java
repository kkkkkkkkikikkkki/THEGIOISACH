//package com.dan.datn.Controller;
//
//import com.dan.datn.Entity.SanPham;
//import com.dan.datn.Service.SanPhamService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/sanpham")
//public class SanPhamController {
//
//    @Autowired
//    private SanPhamService sanPhamService;
//
//    @GetMapping
//    public List<SanPham> getAllSanPhams() {
//        return sanPhamService.getAllSanPhams();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<SanPham> getSanPhamById(@PathVariable String id) {
//        return sanPhamService.getSanPhamById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ResponseEntity<SanPham> createSanPham(@RequestBody SanPham sanPham) {
//        SanPham createdSanPham = sanPhamService.saveSanPham(sanPham);
//        return ResponseEntity.status(201).body(createdSanPham);
//    }
//
////    @PutMapping("/{id}")
////    public ResponseEntity<SanPham> updateSanPham(@PathVariable String id, @RequestBody SanPham sanPham) {
////        return sanPhamService.getSanPhamById(id)
////                .map(existingSanPham -> {
////                    sanPham.setIdSanPham(existingSanPham.getIdSanPham()); // Set ID to ensure we're updating the correct product
////                    return ResponseEntity.ok(sanPhamService.saveSanPham(sanPham));
////                })
////                .orElse(ResponseEntity.notFound().build());
////    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSanPham(@PathVariable String id) {
//        if (sanPhamService.getSanPhamById(id).isPresent()) {
//            sanPhamService.deleteSanPham(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
//}
