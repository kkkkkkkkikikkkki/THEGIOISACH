package com.dan.datn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrangChuController {
    @GetMapping("/trangChu")
    public String showTrangChu(){
        return "index/trangChu";
    }

    @GetMapping ("/dangnhap")
    public String showDangNhap(){
        return "index/dangNhap";
    }

    @GetMapping ("/dangky")
    public String showDangKy(){
        return "index/dangKy";
    }
}
