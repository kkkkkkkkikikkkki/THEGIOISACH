package com.dan.datn.Controller;

import com.dan.datn.Entity.ThongKe;
import com.dan.datn.Service.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ThongKeController {
    @Autowired
    private ThongKeService thongKeService;

        @GetMapping("/thongke")
        public String getThongKe(Model model) {
            List<ThongKe> thongKes = thongKeService.getAllThongKe(); // Lấy dữ liệu từ service

            // Lọc các sản phẩm trùng lặp dựa trên sanPham.id và cộng dồn số lượng, tổng tiền
            Map<Long, ThongKe> uniqueProductsMap = new HashMap<>();

            for (ThongKe thongKe : thongKes) {
                Long productId = thongKe.getThanhToan().getSanPham().getID_san_pham();
                if (uniqueProductsMap.containsKey(productId)) {
                    ThongKe existingThongKe = uniqueProductsMap.get(productId);
                    existingThongKe.getThanhToan().setSoLuong(existingThongKe.getThanhToan().getSoLuong() + thongKe.getThanhToan().getSoLuong());
                    existingThongKe.getThanhToan().setTongTien(existingThongKe.getThanhToan().getTongTien() + thongKe.getThanhToan().getTongTien());
                } else {
                    uniqueProductsMap.put(productId, thongKe);
                }
            }

            // Chuyển đổi map thành danh sách để dễ hiển thị trong Thymeleaf
            List<ThongKe> uniqueThongKes = new ArrayList<>(uniqueProductsMap.values());

            // Tổng doanh thu
            double totalRevenue = uniqueThongKes.stream()
                    .mapToDouble(tk -> tk.getThanhToan().getTongTien())
                    .sum();

            model.addAttribute("thongKes", uniqueThongKes);
            model.addAttribute("totalRevenue", totalRevenue);

            return "index/ThongKeDoanhThu"; // Trả về view tên "thongke"
        }
    }




