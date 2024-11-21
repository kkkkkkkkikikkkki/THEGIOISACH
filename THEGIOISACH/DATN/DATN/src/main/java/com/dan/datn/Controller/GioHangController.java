package com.dan.datn.Controller;

import com.dan.datn.Entity.Hinh;
import com.dan.datn.Entity.SanPham;
import com.dan.datn.Service.ServiceImpl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import static com.dan.datn.Service.ServiceImpl.SanPhamServiceImpl.sanPhamRepository;


@SessionAttributes("username")
@Controller
public class GioHangController {
    private final UserServiceImpl userServiceImpl;
    @Autowired
    private HttpSession session;

    public GioHangController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/giohang")
    public String giohang(Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);

        if (username == null) {
            model.addAttribute("error", "Bạn vui lòng đăng nhập trước khi truy cập giỏ hàng.");
            return "index/dangNhap";
        }
        // Lấy giỏ hàng từ session, nếu chưa có thì tạo mới
        List<SanPham> cart = (List<SanPham>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // Truyền giỏ hàng vào model để hiển thị
        model.addAttribute("cart", cart);
        return "layout/gioHang";  // Trả về view giỏ hàng
    }


    @PostMapping("/cart")
    public String addToCart(@RequestParam("productId") Long productId, Model model) {
        // Tìm sản phẩm trong cơ sở dữ liệu
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);

        if (username == null) {
            model.addAttribute("error", "Bạn vui lòng đăng nhập trước khi truy cập giỏ hàng.");
            return "index/dangNhap";
        }
        SanPham sanpham = sanPhamRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Sản phẩm không tồn tại với ID: " + productId));

        // Lấy giỏ hàng từ session, nếu chưa có thì tạo mới
        List<SanPham> cart = (List<SanPham>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
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
        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        boolean found = false;
        for (SanPham item : cart) {
            if (item.getID_san_pham().equals(sanpham.getID_san_pham())) {
                found = true;
                break;
            }
        }
        // Nếu sản phẩm chưa có trong giỏ hàng, thêm vào giỏ hàng
        if (!found) {
            cart.add(sanpham);
            session.setAttribute("cart", cart);  // Lưu lại giỏ hàng mới vào session
            session.setAttribute("success", "Sản phẩm đã được thêm vào giỏ hàng");
        } else {
            session.setAttribute("error", "Sản phẩm này đã có trong giỏ hàng");
        }
        // Truyền giỏ hàng vào model
        model.addAttribute("sanpham", sanpham);
        model.addAttribute("username", session.getAttribute("username"));

        // Chuyển hướng về trang chi tiết sản phẩm với thông báo thành công
        return "redirect:/sanpham/" + productId;
    }

    @PostMapping("/removeFromCart")
    public String removeFromCart(@RequestParam("productId") Long productId, Model model) {
        // Lấy giỏ hàng từ session
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);

        if (username == null) {
            model.addAttribute("error", "Bạn vui lòng đăng nhập trước khi truy cập giỏ hàng.");
            return "index/dangNhap";
        }
        List<SanPham> cart = (List<SanPham>) session.getAttribute("cart");
        if (cart != null) {
            // Tìm sản phẩm trong giỏ hàng và xóa nó
            cart.removeIf(item -> item.getID_san_pham().equals(productId));

            // Cập nhật lại giỏ hàng trong session
            session.setAttribute("cart", cart);

            model.addAttribute("success", "Sản phẩm đã được xóa khỏi giỏ hàng");
        } else {
            model.addAttribute("error", "Giỏ hàng trống");
        }

        // Truyền giỏ hàng vào model để hiển thị
        model.addAttribute("cart", cart);
        model.addAttribute("username", session.getAttribute("username"));

        return "layout/gioHang";  // Trả về view giỏ hàng
    }
    @PostMapping("/update-cart")
    public ResponseEntity<Void> updateCart(@RequestBody Map<String, Integer> payload, HttpSession session) {
        Integer quantity = payload.get("quantity");

        // Cập nhật session với số lượng mới
        session.setAttribute("cartQuantity", quantity);
        return ResponseEntity.ok().build();
    }
}
