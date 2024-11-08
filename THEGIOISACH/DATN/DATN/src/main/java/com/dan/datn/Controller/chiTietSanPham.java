package com.dan.datn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class chiTietSanPham {

    @GetMapping("/sanpham")
    public String viewSanPhamPage() {
        // Tên trả về phải trùng với tên của file HTML, không bao gồm phần mở rộng ".html"
        return "index/chiTietSanPham";
    }
}
