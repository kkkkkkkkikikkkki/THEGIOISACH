package com.dan.datn.Controller;

import com.dan.datn.Entity.Hinh;
import com.dan.datn.Entity.TheLoai;
import com.dan.datn.Service.HinhService;
import com.dan.datn.Service.ServiceImpl.SanPhamServiceImpl;
import com.dan.datn.Entity.SanPham;
import com.dan.datn.Service.ServiceImpl.TheLoaiServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
public class QuanLySanPhamController {

    private final SanPhamServiceImpl sanPhamService;
    private final HinhService hinhService;
    private final TheLoaiServiceImpl theLoaiService;

    @Autowired
    public QuanLySanPhamController(SanPhamServiceImpl sanPhamService, HinhService hinhService, TheLoaiServiceImpl theLoaiService) {
        this.sanPhamService = sanPhamService;
        this.hinhService = hinhService;
        this.theLoaiService = theLoaiService;
    }

    @GetMapping("/quanlysanpham")
    public String quanlysanpham(Model model, HttpSession session,
                                RedirectAttributes redirectAttributes) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng đăng nhập với vai trò admin để xem quản lý sản phẩm.");
            return "redirect:/dangnhapadmin";
        }
        List<SanPham> products = sanPhamService.getAllSanPham();  // Lấy danh sách sản phẩm từ service
        model.addAttribute("products", products);  // Thêm sản phẩm vào model để Thymeleaf sử dụng
        return "layout/Quanlysanpham";  // Trả về view
    }

    @GetMapping("/api/image/{id}")
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


    @PostMapping("/addProduct")
    public String addProduct(@RequestParam(value = "imageMain", required = false) MultipartFile imageMain,
                             @RequestParam("image1") MultipartFile image1,
                             @RequestParam("image2") MultipartFile image2,
                             @RequestParam("image3") MultipartFile image3,
                             @RequestParam("image4") MultipartFile image4,
                             @RequestParam("name") String name,
                             @RequestParam("category") String category,
                             @RequestParam("author") String author,
                             @RequestParam("publisher") String publisher,
                             @RequestParam("productionDate") String productionDate,
                             @RequestParam("price") Integer price,
                             @RequestParam("description") String description,
                             @RequestParam("stockQuantity") Integer stockQuantity,
                             @RequestParam("soluongtonkho") Integer soluongtonkho,
                             @RequestParam("soluongdaban") Integer soluongdaban,
                             Model model, RedirectAttributes redirectAttributes) {
        try {
            // 1. Thêm hình ảnh vào bảng Hinh
            Hinh newHinh = hinhService.saveMultipleImages(
                    imageMain.getBytes(), image1.getBytes(), image2.getBytes(), image3.getBytes(), image4.getBytes());

            // 2. Thêm thể loại vào bảng TheLoai nếu chưa có
            TheLoai theLoai = theLoaiService.findByCategoryName(category);
            if (theLoai == null) {
                model.addAttribute("error", "Thêm sản phẩm không thành công" );

                theLoai.setTheLoai(category);
                // Lưu thể loại mới vào DB
            }

            // 3. Tạo sản phẩm mới
            SanPham sanPham = new SanPham();
            sanPham.setTenSach(name);
            sanPham.setTacGia(author);
            sanPham.setNhaXuatBan(publisher);
            sanPham.setNsx(java.sql.Date.valueOf(productionDate));
            sanPham.setGia(price);
            sanPham.setMoTa(description);
            sanPham.setSoLuongTongSanPham(stockQuantity); // Đảm bảo thông tin tổng số lượng sản phẩm được lưu
            sanPham.setTheLoai(theLoai);
            sanPham.setHinh(newHinh);  // Gán hình ảnh đã lưu vào sản phẩms
            sanPham.setSoLuongTonKho(soluongtonkho);
            sanPham.setSoLuongDaBan(soluongdaban);

            // 4. Lưu sản phẩm vào DB
            sanPhamService.saveSanPham(sanPham);

            // Thêm thông báo thành công vào model
            redirectAttributes.addFlashAttribute("success", "Sản phẩm đã được thêm thành công!");
            return "redirect:/quanlysanpham";  // Chuyển đến trang quản lý sản phẩm

        } catch (Exception e) {
            // Xử lý lỗi và hiển thị thông báo lỗi cho người dùng
            e.printStackTrace();

            // Gửi lỗi cụ thể đến giao diện người dùng
            String errorMessage = "Sản phẩm đã được thêm không thành công! Lỗi hệ thống: " + e.getMessage();
            redirectAttributes.addFlashAttribute("error", errorMessage);            return "redirect:/quanlysanpham";  // Quay lại trang thêm sản phẩm hoặc trang quản lý sản phẩm với thông báo lỗi
        }
    }



}
