//package com.dan.datn.Controller;
//
//import com.dan.datn.Entity.SanPham;
//import com.dan.datn.Service.SanPhamService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Controller
//public class SanPhamController {
//
//    @Autowired
//    private SanPhamService sanPhamService;
//
//    @GetMapping("/search")
//    public String searchSanPham(
//            @RequestParam(required = false) String tenSach,
//            @RequestParam(required = false) String theLoai,
//            Model model) {
//
//        List<SanPham> sanPhams ;
//        if (tenSach != null && theLoai != null) {
//            sanPhams = sanPhamService.searchByTenSachAndTheLoai(tenSach, theLoai);
//        } else if (tenSach != null) {
//            sanPhams = sanPhamService.searchByTenSach(tenSach);
//        } else if (theLoai != null) {
//            sanPhams = sanPhamService.searchByTheLoai(theLoai);
//        }else {
//            sanPhams = List.of();
//        }
//
//        model.addAttribute("sanPhams", sanPhams);
//        model.addAttribute("tenSach", tenSach);
//        model.addAttribute("theLoai", theLoai);
//        return "index/sanPhamSauTimKiem";
//    }
//}
//
//
