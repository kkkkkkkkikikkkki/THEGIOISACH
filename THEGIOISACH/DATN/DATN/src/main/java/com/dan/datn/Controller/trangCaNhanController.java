package com.dan.datn.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("username")
@Controller
public class trangCaNhanController {
//    @GetMapping("/trangcanhan")
//    public String trangCaNhan(Model model) {
//        return "index/trangCaNhan";
//    }


    @GetMapping("/trangcanhan")
    public String trangCaNhan(HttpSession session, Model model) {
        // Kiểm tra nếu username không tồn tại trong session
        String username = (String) session.getAttribute("username");

        if (username == null) {
            // Nếu chưa đăng nhập, hiển thị thông báo và chuyển hướng đến trang đăng nhập
            model.addAttribute("error", "Bạn vui lòng đăng nhập trước khi truy cập trang cá nhân.");
            return "index/dangNhap"; // Redirect to login page
        }

        // Nếu đã đăng nhập, hiển thị trang cá nhân
        model.addAttribute("username", username);
        return "index/trangCaNhan"; // Return to personal page
    }


    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Xóa tất cả dữ liệu trong session
        return "redirect:/login"; // Chuyển hướng về trang đăng nhập
    }
}
