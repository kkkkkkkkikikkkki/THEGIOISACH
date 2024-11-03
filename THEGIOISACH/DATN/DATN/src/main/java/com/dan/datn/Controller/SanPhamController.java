package com.dan.datn.Controller;

import com.dan.datn.Entity.SanPham;
import com.dan.datn.Service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SanPhamController {

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/san-pham")
    public String showSanPhamPage(Model model) {
        // Use the sanPhamService instance to get all products
        List<SanPham> sanPhamList = sanPhamService.getAllSanPham();
        model.addAttribute("sanPhamList", sanPhamList);
        return "index/trangChu"; // Make sure this matches your HTML file
    }
}
