package com.dan.datn.Controller;

import com.dan.datn.Service.ServiceImpl.SanPhamServiceImpl;
import com.dan.datn.Entity.SanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@Controller
public class QuanLySanPhamController {

    private final SanPhamServiceImpl sanPhamService;

    @Autowired
    public QuanLySanPhamController(SanPhamServiceImpl sanPhamService) {
        this.sanPhamService = sanPhamService;
    }

    @GetMapping("/quanlysanpham")
    public String quanlysanpham(Model model) {
        List<SanPham> products = sanPhamService.getAllSanPham();  // Lấy danh sách sản phẩm từ service
        model.addAttribute("products", products);  // Thêm sản phẩm vào model để Thymeleaf sử dụng
        return "layout/Quanlysanpham";  // Trả về view
    }



    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewProductDetails(@RequestParam("id") Long id, Model model) {
        // Lấy thông tin sản phẩm từ ID
        SanPham product = sanPhamService.getSanPhamById(id);

        // Kiểm tra nếu không tìm thấy sản phẩm
        if (product == null) {
            model.addAttribute("error", "Sản phẩm không tồn tại!");
            return "error";  // Chuyển đến trang lỗi (nếu cần)
        }

        // Thêm thông tin sản phẩm vào model
        model.addAttribute("product", product);

        // Trả về trang chi tiết sản phẩm (có thể là layout/Quanlysanpham hoặc một view khác)
        return "layout/Quanlysanpham";  // Tên của view (JSP hoặc Thymeleaf template)
    }
    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        SanPham sanPham = sanPhamService.getSanPhamById(id);
        if (sanPham == null || sanPham.getHinh() == null) {
            return ResponseEntity.notFound().build();
        }
        byte[] imageBytes = Base64.getDecoder().decode(sanPham.getHinh().getBase64MainImage());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageBytes);
    }




}
