package com.dan.datn.Controller;

import com.dan.datn.Entity.ThongKe;
import com.dan.datn.Service.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ThongKeController {
    @Autowired
    private ThongKeService thongKeService;

    @GetMapping("/thongke")
    public String getThongKe(Model model) {

        // Tính toán và cập nhật tổng doanh thu vào bảng ThongKe
        thongKeService.calculateAndUpdateTotalRevenue();

        // Lấy tất cả thống kê sau khi cập nhật
        List<ThongKe> thongKes = thongKeService.getAllThongKe();
        if (thongKes.isEmpty()) {
            model.addAttribute("message", "Thống kê trống");
        } else {
            model.addAttribute("thongKes", thongKes);
        }
        // Thêm danh sách thống kê vào model


        // Trả về view hiển thị
        return "index/ThongKeDoanhThu"; // View này sẽ hiển thị danh sách thống kê
    }



}
