package com.dan.datn.Controller;

import com.dan.datn.Entity.Hinh;
import com.dan.datn.Entity.SanPham;
import com.dan.datn.Service.ServiceImpl.SanPhamService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static com.dan.datn.Service.ServiceImpl.SanPhamService.sanPhamRepository;

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
        session.setAttribute("sanpham", sanpham);  // Lưu sản phẩm vào session
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

        // Lấy danh sách các sản phẩm liên quan có cùng thể loại (trừ sản phẩm hiện tại)
        List<SanPham> sanPhamLienQuan = sanPhamService.getRandomProducts();
        for (SanPham sp : sanPhamLienQuan) {
            if (sp.getHinh() != null && sp.getHinh().getHinhMain() != null) {
                String base64Image = Base64.getEncoder().encodeToString(sp.getHinh().getHinhMain());
                sp.getHinh().setBase64Image(base64Image);
            }
        }
        model.addAttribute("sanPhamLienQuan", sanPhamLienQuan);
        String successMessage = (String) session.getAttribute("success");
        String errorMessage = (String) session.getAttribute("error");

        if (successMessage != null) {
            model.addAttribute("success", successMessage);
            session.removeAttribute("success"); // Xóa khỏi session
        }

        if (errorMessage != null) {
            model.addAttribute("error", errorMessage);
            session.removeAttribute("error"); // Xóa khỏi session
        }

        return "index/chiTietSanPham";  // Chuyển hướng đến trang chi tiết sản phẩm
    }
}
