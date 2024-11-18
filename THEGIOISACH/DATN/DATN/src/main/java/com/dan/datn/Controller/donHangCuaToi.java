//package com.dan.datn.Controller;
//
//import com.dan.datn.Entity.HoaDon;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@Controller
//public class donHangCuaToi {
//
//
//private final HoaDonService hoaDonService;
//    @Autowired
//    public donHangCuaToi(HoaDonService hoaDonService) {
//        this.hoaDonService = hoaDonService;
//    }
//
//    @GetMapping("/donhangcuatoi")
//    public String getOrderDetails(Model model, HttpSession session) {
//        String username = (String) session.getAttribute("username");
//
//        if (username == null) {
//            model.addAttribute("error", "Bạn vui lòng đăng nhập trước khi truy cập đơn hàng của tôi.");
//            return "index/dangNhap";
//        }
//
//        model.addAttribute("username", username);
//
//
//        // Lấy username từ session
//        List<HoaDon> orderDetails = hoaDonService.getOrderDetailsByUsername(username);
//        model.addAttribute("orderDetails", orderDetails);
//        return "layout/Donhangcuatoi"; // Trả về tên view
//    }
//
//
//
////    @GetMapping("/donhangcuatoi")
////    public String getOrderDetails(Model model, HttpSession session) {
////        String username = (String) session.getAttribute("username");
////
////        if (username == null) {
////            model.addAttribute("error", "Bạn vui lòng đăng nhập trước khi truy cập đơn hàng của tôi.");
////            return "redirect:/login";
////        }
////
////        model.addAttribute("username", username);
////
//////        List<HoaDonChiTiet> orderDetails = hoaDonChiTietService.getChiTietHoaDonByUsername(username);
//////        model.addAttribute("orderDetails", orderDetails);
////
////        return "layout/donhangcuatoi";
////    }
//
//
//
//
//}
