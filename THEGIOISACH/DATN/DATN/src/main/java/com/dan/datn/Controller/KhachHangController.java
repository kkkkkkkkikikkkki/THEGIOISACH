package com.dan.datn.Controller;



import com.dan.datn.Service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/khachhang")
public class KhachHangController {

    private final KhachHangService khachHangService;

    @Autowired
    public KhachHangController(KhachHangService khachHangService) {
        this.khachHangService = khachHangService;
    }

    @GetMapping
    public List<KhachHang> layTatCaKhachHang() {
        return khachHangService.layTatCaKhachHang();
    }

    @GetMapping("/{id}")
    public KhachHang layKhachHangTheoId(@PathVariable Long idKhachHang) {
        return khachHangService.layKhachHangTheoId(idKhachHang);
    }
}

