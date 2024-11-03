package com.dan.datn.Controller;

import com.dan.datn.Entity.SanPham;
import com.dan.datn.Service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TrangChuController {

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/trangchu")
    public String showTrangChu(Model model) {
        List<SanPham> sanPhamList = sanPhamService.getAllSanPham();
        model.addAttribute("sanPhamList", sanPhamList);
        return "index/trangChu";
    }

    @GetMapping("/login")
    public String showDangNhap() {
        return "index/dangNhap";
    }

//    @GetMapping("dangky")
//    public String showDangKy() {
//        return "index/dangKy";
//    }
}
