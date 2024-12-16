package com.dan.datn.Controller;

import com.dan.datn.Entity.Hinh;
import com.dan.datn.Entity.TheLoai;
import com.dan.datn.Entity.SanPham;
import com.dan.datn.Service.HinhService;
import com.dan.datn.Service.SanPhamService;
import com.dan.datn.Service.TheLoaiService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Base64;
import java.util.List;

@Controller
public class QuanLySanPhamController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private HinhService hinhService;
    @Autowired
    private TheLoaiService theLoaiService;


    @GetMapping("/quanlysanpham")
    public String quanlysanpham(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        String ten = (String) session.getAttribute("ten");
        if (ten == null) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng đăng nhập với vai trò admin để xem quản lý sản phẩm.");
            return "redirect:/dangnhapadmin";
        }
        List<SanPham> products = sanPhamService.getAllSanPham();  // Sử dụng phương thức từ SanPhamService
        model.addAttribute("products", products);  // Thêm sản phẩm vào model để Thymeleaf sử dụng
        model.addAttribute("ten", ten);
        return "layout/Quanlysanpham";  // Trả về view
    }
    @PostMapping("/updateProduct")
    public String updateProduct(
            @RequestParam("modalProductid") Long id,
            @RequestParam("modalProductName") String name,
            @RequestParam("modalProductCategory") String categoryName,
            @RequestParam("modalProductAuthor") String author,
            @RequestParam("modalProductPublisher") String publisher,
            @RequestParam("modalProductDesc") String description,
            @RequestParam("modalProductPrice") Integer price,
            @RequestParam("modalProductSoldQty") Integer soldQuantity,
            @RequestParam("modalProductStockQty") Integer stockQuantity,
            @RequestParam("modalProductTotalQty") Integer totalQuantity,
            Model model, HttpSession session, RedirectAttributes redirectAttributes){

        try {
            // Xử lý cập nhật sản phẩm như bình thường
            SanPham product = sanPhamService.getSanPhamById(id);
            if (product == null) {
                return "redirect:/quanlysanpham";
            }

            // Cập nhật thông tin sản phẩm
            product.setTenSach(name);
            product.setTacGia(author);
            product.setNhaXuatBan(publisher);
            product.setMoTa(description);
            product.setGia(price);
            product.setSoLuongTonKho(stockQuantity);
            product.setSoLuongDaBan(soldQuantity);
            product.setSoLuongTongSanPham(totalQuantity);

            // Cập nhật thể loại
            TheLoai theLoai = theLoaiService.findByCategoryName(categoryName);
            if (theLoai == null) {
                return "redirect:/quanlysanpham";
            }
            product.setTheLoai(theLoai);

            // Lưu sản phẩm đã cập nhật
            sanPhamService.saveSanPham(product);
            redirectAttributes.addFlashAttribute("success", "Thay đổi thông tin thành công.");
            return "redirect:/quanlysanpham";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thay đổi thông tin không thành công.");
            return "redirect:/quanlysanpham";
        }
    }
    @GetMapping("/api/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        try {
            SanPham sanPham = sanPhamService.getSanPhamById(id);
            if (sanPham == null || sanPham.getHinh() == null) {
                return ResponseEntity.notFound().build();
            }
            byte[] imageBytes = Base64.getDecoder().decode(sanPham.getHinh().getBase64MainImage());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(imageBytes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/quanlysanpham/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean deleted = sanPhamService.deleteProductById(id);

        if (deleted) {
            return ResponseEntity.ok().build();  // Return HTTP 200 OK
        } else {
            return ResponseEntity.status(404).build();  // Return HTTP 404 Not Found
        }
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam(value = "imageMain", required = false) MultipartFile imageMain,
                             @RequestParam(value = "image1", required = false) MultipartFile image1,
                             @RequestParam(value = "image2", required = false) MultipartFile image2,
                             @RequestParam(value = "image3", required = false) MultipartFile image3,
                             @RequestParam(value = "image4", required = false) MultipartFile image4,
                             @RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "category", required = false) String category,
                             @RequestParam(value = "author", required = false) String author,
                             @RequestParam(value = "publisher", required = false) String publisher,
                             @RequestParam(value = "productionDate", required = false) String productionDate,
                             @RequestParam(value = "price", required = false) Integer price,
                             @RequestParam(value = "description", required = false) String description,
                             @RequestParam(value = "stockQuantity", required = false) Integer stockQuantity,
                             @RequestParam(value = "soluongtonkho", required = false) Integer soluongtonkho,
                             @RequestParam(value = "soluongdaban", required = false) Integer soluongdaban,
                             Model model, RedirectAttributes redirectAttributes) {
        try {
            if (imageMain == null || imageMain.isEmpty()
            && image1 == null || image1.isEmpty()
            && name == null || name.trim().isEmpty()
            && category == null || category.trim().isEmpty()
            && author == null || author.trim().isEmpty()
            && publisher == null || publisher.trim().isEmpty()
            && productionDate == null || productionDate.trim().isEmpty()
            && stockQuantity == null
            && soluongtonkho == null
            && soluongdaban == null
            && description == null || description.trim().isEmpty()
            && price == null

            ) {
                redirectAttributes.addFlashAttribute("error", "Bạn chưa điền dữ liệu.");
                return "redirect:/quanlysanpham";
            }
            // Kiểm tra các trường không được để trống
            if (imageMain == null || imageMain.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Hình ảnh chính không được để trống.");
                return "redirect:/quanlysanpham";
            }
            if (image1 == null || image1.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Hình ảnh phụ thứ nhất không được để trống.");
                return "redirect:/quanlysanpham";
            }
            if (name == null || name.trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Tên sản phẩm không được để trống.");
                return "redirect:/quanlysanpham";
            }
            if (category == null || category.trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Thể loại không được để trống.");
                return "redirect:/quanlysanpham";
            }
            if (author == null || author.trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Tác giả không được để trống.");
                return "redirect:/quanlysanpham";
            }
            if (publisher == null || publisher.trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Nhà xuất bản không được để trống.");
                return "redirect:/quanlysanpham";
            }
            if (productionDate == null || productionDate.trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Ngày sản xuất không được để trống.");
                return "redirect:/quanlysanpham";
            }
            if (price == null) {
                redirectAttributes.addFlashAttribute("error", "Giá sản phẩm không được để trống.");
                return "redirect:/quanlysanpham";
            }
            if (description == null || description.trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Mô tả sản phẩm không được để trống.");
                return "redirect:/quanlysanpham";
            }
            if (stockQuantity == null) {
                redirectAttributes.addFlashAttribute("error", "Số lượng sản phẩm không được để trống.");
                return "redirect:/quanlysanpham";
            }
            if (soluongtonkho == null) {
                redirectAttributes.addFlashAttribute("error", "Số lượng tồn kho không được để trống.");
                return "redirect:/quanlysanpham";
            }
            if (soluongdaban == null) {
                redirectAttributes.addFlashAttribute("error", "Số lượng đã bán không được để trống.");
                return "redirect:/quanlysanpham";
            }

            // 1. Thêm hình ảnh vào bảng Hinh
            Hinh newHinh = hinhService.saveMultipleImages(
                    imageMain.getBytes(), image1.getBytes(), image2.getBytes(), image3.getBytes(), image4.getBytes());

            // 2. Thêm thể loại vào bảng TheLoai
            TheLoai theLoai = theLoaiService.findByCategoryName(category);
            if (theLoai == null) {
                redirectAttributes.addFlashAttribute("error", "Thể loại không tồn tại.");
                return "redirect:/quanlysanpham";            }
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
            sanPham.setHinh(newHinh);  // Gán hình ảnh đã lưu vào sản phẩm
            sanPham.setSoLuongTonKho(soluongtonkho);
            sanPham.setSoLuongDaBan(soluongdaban);

            // 4. Lưu sản phẩm vào DB
            sanPhamService.saveSanPham(sanPham);  // Sử dụng phương thức từ SanPhamService

            // Thêm thông báo thành công vào model
            redirectAttributes.addFlashAttribute("success", "Sản phẩm đã được thêm thành công!");
            return "redirect:/quanlysanpham";  // Chuyển đến trang quản lý sản phẩm

        } catch (Exception e) {
            // Xử lý lỗi và hiển thị thông báo lỗi cho người dùng
            e.printStackTrace();

            // Gửi lỗi cụ thể đến giao diện người dùng
            String errorMessage = "Sản phẩm đã được thêm không thành công! Lỗi hệ thống: " + e.getMessage();
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/quanlysanpham";  // Quay lại trang thêm sản phẩm hoặc trang quản lý sản phẩm với thông báo lỗi
        }
    }
}
