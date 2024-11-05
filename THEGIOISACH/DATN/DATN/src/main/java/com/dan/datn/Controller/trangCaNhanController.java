package com.dan.datn.Controller;

import com.dan.datn.Entity.User;
import com.dan.datn.Service.ServiceImpl.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@SessionAttributes("username")
@Controller
public class trangCaNhanController {
    private final UserServiceImpl userServiceImpl;

    public trangCaNhanController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
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
    @GetMapping("/thongtintaikhoan")
    public String getThongTinCaNhan(HttpSession session, Model model) {
        // Lấy username từ session
        String username = (String) session.getAttribute("username");

        if (username == null) {
            // Nếu chưa đăng nhập, hiển thị thông báo và chuyển hướng đến trang đăng nhập
            model.addAttribute("error", "Bạn vui lòng đăng nhập trước khi truy cập thông tin cá nhân.");
            return "index/dangNhap"; // Redirect to login page
        }

        // Tìm người dùng theo tên
        Optional<User> userOptional = userServiceImpl.getUserByTen(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user); // Truyền dữ liệu người dùng vào model
        } else {
            model.addAttribute("error", "Người dùng không tồn tại.");
        }

        return "layout/Thongtincanhan"; // Trả về view
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        session.invalidate(); // Xóa tất cả dữ liệu trong session

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // Xóa cookie bằng cách thiết lập thời gian sống của nó về 0
                cookie.setMaxAge(0);
                cookie.setPath("/"); // Đặt path để cookie bị xóa trên toàn bộ ứng dụng
                response.addCookie(cookie); // Gửi cookie đã được xóa về client
            }
        }
        return "redirect:/login"; // Chuyển hướng về trang đăng nhập
    }
}
