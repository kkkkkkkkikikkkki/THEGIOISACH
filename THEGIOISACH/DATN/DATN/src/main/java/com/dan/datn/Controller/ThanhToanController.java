package com.dan.datn.Controller;

import com.dan.datn.Entity.SanPham;
import com.dan.datn.Service.ServiceImpl.HoaDonChiTietServiceImpl;
import com.dan.datn.Service.ServiceImpl.ThanhToanServiceImpl;
import com.dan.datn.Service.ServiceImpl.UserServiceImpl;
import com.dan.datn.dto.ProductDTO;
import com.dan.datn.dto.ThanhToanDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SessionAttributes("username")
@Controller
@RequestMapping("/api")
public class ThanhToanController {

    private final UserServiceImpl userServiceImpl;
    private final HoaDonChiTietServiceImpl hoaDonChiTietServiceImpl;
    private final ThanhToanServiceImpl thanhToanServiceImpl;
    @Autowired
    private HttpSession session;

    @Autowired
    public ThanhToanController(UserServiceImpl userServiceImpl, HoaDonChiTietServiceImpl hoaDonChiTietServiceImpl, ThanhToanServiceImpl thanhToanServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.hoaDonChiTietServiceImpl = hoaDonChiTietServiceImpl;
        this.thanhToanServiceImpl = thanhToanServiceImpl;
    }

    @GetMapping("/checkout")
    public String showCheckoutPage(Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        Integer cartQuantity = (Integer) session.getAttribute("cartQuantity");

        if (username == null) {
            model.addAttribute("error", "Bạn vui lòng đăng nhập trước khi truy cập giỏ hàng.");
            return "index/dangNhap";
        }

        // Lấy giỏ hàng từ session
        List<SanPham> cart = (List<SanPham>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // Tính tổng số tiền giỏ hàng (giá * số lượng)
        double total = cart.stream()
                .mapToDouble(sanPham -> sanPham.getGia() * sanPham.getSoLuongTongSanPham()) // Tính tổng giá trị của mỗi sản phẩm
                .sum();  // Cộng dồn cho tất cả sản phẩm
        model.addAttribute("cartQuantity", cartQuantity != null ? cartQuantity : 1);
        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        return "layout/checkout";  // Trang checkout
    }


    // Xử lý thanh toán
    @PostMapping("/processCheckout")
    public String processCheckout(@RequestParam("tinh_tp") String tinh_tp,
                                  @RequestParam("quan_huyen") String quan_huyen,
                                  @RequestParam("phuong_xa") String phuong_xa,
                                  @RequestParam("soDienThoai") String soDienThoai,
                                  @RequestParam("diaChiChiTiet") String diaChiChiTiet,
                                  @RequestParam("phuong_thuc_thanh_toan") String phuong_thuc_thanh_toan,
                                  @RequestParam("total") double total,
                                  Model model) {

        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);

        if (username == null) {
            model.addAttribute("error", "Bạn vui lòng đăng nhập trước khi thanh toán.");
            return "index/dangNhap";
        }

        // Lấy giỏ hàng từ session
        List<SanPham> cart = (List<SanPham>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // Chuyển giỏ hàng sang DTO
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (SanPham sanPham : cart) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(sanPham.getID_san_pham());
            productDTO.setTenSach(sanPham.getTenSach());
            productDTO.setGia(sanPham.getGia());
            productDTOs.add(productDTO);
        }

        // Tạo DTO cho thanh toán
        ThanhToanDTO thanhToanDTO = new ThanhToanDTO();
        thanhToanDTO.setTinh_tp(tinh_tp);
        thanhToanDTO.setQuan_huyen(quan_huyen);
        thanhToanDTO.setPhuong_xa(phuong_xa);
        thanhToanDTO.setSoDienThoai(soDienThoai);
        thanhToanDTO.setDiaChiChiTiet(diaChiChiTiet);
        thanhToanDTO.setPhuong_thuc_thanh_toan(phuong_thuc_thanh_toan);
        thanhToanDTO.setTongTien(total);
        thanhToanDTO.setProducts(productDTOs);

        // Tạo thanh toán mới thông qua service
        thanhToanServiceImpl.createThanhToan(thanhToanDTO);

        // Sau khi thanh toán thành công, xóa giỏ hàng khỏi session
        session.removeAttribute("cart");
        session.removeAttribute("cartQuantity");

        // Chuyển hướng đến trang thành công
        return "layout/checkoutSuccess";// Trang thành công
    }

    @GetMapping("/checkoutSuccess")
    public String showCheckoutSuccess(Model model) {

        return "layout/checkoutSuccess";
    }

}
