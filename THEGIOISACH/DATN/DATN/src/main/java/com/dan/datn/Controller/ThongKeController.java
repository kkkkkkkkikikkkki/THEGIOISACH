package com.dan.datn.Controller;

import com.dan.datn.Entity.ThongKe;
import com.dan.datn.Service.ServiceImpl.UserServiceImpl;
import com.dan.datn.Service.ThongKeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ThongKeController {
    @Autowired
    private ThongKeService thongKeService;
    @Autowired
    private UserServiceImpl userServiceImpl;



    @GetMapping("/thongke")
    public String getThongKe(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng đăng nhập với vai trò admin để xem thống kê.");
            return "redirect:/dangnhapadmin";
        }

        // Nếu đã đăng nhập
        model.addAttribute("email", email);

        List<ThongKe> thongKes = thongKeService.getAllThongKe();

        // Lọc các sản phẩm trùng lặp
        Map<Long, ThongKe> uniqueProductsMap = new HashMap<>();
        for (ThongKe thongKe : thongKes) {
            Long productId = thongKe.getThanhToan().getSanPham().getID_san_pham();
            if (uniqueProductsMap.containsKey(productId)) {
                ThongKe existingThongKe = uniqueProductsMap.get(productId);
                existingThongKe.getThanhToan().setSoLuong(
                        existingThongKe.getThanhToan().getSoLuong() + thongKe.getThanhToan().getSoLuong()
                );
                existingThongKe.getThanhToan().setTongTien(
                        existingThongKe.getThanhToan().getTongTien() + thongKe.getThanhToan().getTongTien()
                );
            } else {
                uniqueProductsMap.put(productId, thongKe);
            }
        }

        List<ThongKe> uniqueThongKes = new ArrayList<>(uniqueProductsMap.values());
        double totalRevenue = uniqueThongKes.stream()
                .mapToDouble(tk -> tk.getThanhToan().getTongTien())
                .sum();

        model.addAttribute("thongKes", uniqueThongKes);
        model.addAttribute("totalRevenue", totalRevenue);

        return "index/ThongKeDoanhThu";
    }


}




