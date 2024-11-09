package com.dan.datn.Controller;

import com.dan.datn.Entity.SanPham;
import com.dan.datn.Service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import jakarta.servlet.http.HttpSession;

@Controller
public class TrangChuController {

    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private HttpSession session;

    @GetMapping("/trangchu")
    public String showTrangChu(Model model){
        List<SanPham> sanphams = sanPhamService.getSanPhamByIdRange();
        model.addAttribute("sanphams", sanphams);
        // Chuyển đổi hình ảnh sang Base64
        for (SanPham sp : sanphams) {
            if (sp.getHinh() != null && sp.getHinh().getHinhMain() != null) {
                String base64Image = Base64.getEncoder().encodeToString(sp.getHinh().getHinhMain());
                sp.getHinh().setBase64Image(base64Image);
            }
        }
        model.addAttribute("sanphams", sanphams);

        // Lấy username từ session
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "index/trangChu";
    }

    @GetMapping ("/dangnhap")
    public String showDangNhap(){
        return "index/dangNhap";
    }

    @GetMapping ("/dangkytaikhoan")
    public String showDangKy(){
        return "index/dangKy";
    }

}

