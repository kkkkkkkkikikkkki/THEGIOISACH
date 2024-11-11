package com.dan.datn.Controller;

import com.dan.datn.Entity.Hinh;
import com.dan.datn.Entity.SanPham;
import com.dan.datn.Service.SanPhamService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Base64;

import static com.dan.datn.Service.SanPhamService.sanPhamRepository;

@Controller
public class ChiTietSanPhamController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private HttpSession session;
    @GetMapping("/sanpham/{id}")
    public String viewProductDetails(@PathVariable("id") Long id, Model model) {
        SanPham sanpham = sanPhamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sản phẩm không tồn tại với ID: " + id));
        model.addAttribute("sanpham", sanpham);
        // Kiểm tra sản phẩm có hình ảnh không và chuyển các hình ảnh thành base64
        if (sanpham.getHinh() != null) {
            Hinh hinh = sanpham.getHinh();

            // Chuyển đổi hình ảnh sang Base64
            String base64MainImage = Base64.getEncoder().encodeToString(hinh.getHinhMain());
            sanpham.getHinh().setBase64Image(base64MainImage);

            // Chuyển đổi các hình thu nhỏ sang base64
            String base64Image1 = Base64.getEncoder().encodeToString(hinh.getHinh1());
            String base64Image2 = Base64.getEncoder().encodeToString(hinh.getHinh2());
            String base64Image3 = Base64.getEncoder().encodeToString(hinh.getHinh3());
            String base64Image4 = Base64.getEncoder().encodeToString(hinh.getHinh4());

            model.addAttribute("base64Image1", base64Image1);
            model.addAttribute("base64Image2", base64Image2);
            model.addAttribute("base64Image3", base64Image3);
            model.addAttribute("base64Image4", base64Image4);
        }
        // Lấy username từ session
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "index/chiTietSanPham";  // Chuyển hướng đến trang chi tiết sản phẩm
    }
}
