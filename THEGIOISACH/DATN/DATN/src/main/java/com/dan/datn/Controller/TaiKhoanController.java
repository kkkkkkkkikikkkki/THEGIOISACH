package com.dan.datn.Controller;

import com.dan.datn.Entity.KhachHang;
import com.dan.datn.Entity.NguoiBan;
import com.dan.datn.Service.KhachHangService;
import com.dan.datn.Service.NguoiBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/taikhoan")
public class TaiKhoanController {

    private final KhachHangService khachHangService;
    private final NguoiBanService nguoiBanService;

    @Autowired
    public TaiKhoanController(KhachHangService khachHangService, NguoiBanService nguoiBanService) {
        this.khachHangService = khachHangService;
        this.nguoiBanService = nguoiBanService;
    }

    @GetMapping
    public List<Object> layTatCaTaiKhoan() {
        List<Object> taiKhoans = new ArrayList<>();

        // Lấy danh sách khách hàng
        List<KhachHang> khachHangs = khachHangService.layTatCaKhachHang();
        taiKhoans.addAll(khachHangs);

        // Lấy danh sách người bán
        List<NguoiBan> nguoiBans = nguoiBanService.layTatCaNguoiBan();
        taiKhoans.addAll(nguoiBans);

        return taiKhoans;
    }

    @GetMapping("/{id}")
    public Object layTaiKhoanTheoId(@PathVariable Long id) {
        KhachHang khachHang = khachHangService.layKhachHangTheoId(id);
        if (khachHang != null) {
            return khachHang;
        }

        NguoiBan nguoiBan = nguoiBanService.layNguoiBanTheoId(id);
        if (nguoiBan != null) {
            return nguoiBan;
        }

        return null; // Hoặc có thể ném một lỗi nếu không tìm thấy
    }
}
