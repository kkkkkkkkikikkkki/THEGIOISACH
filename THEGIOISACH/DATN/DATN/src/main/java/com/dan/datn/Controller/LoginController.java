package com.dan.datn.Controller;

import com.dan.datn.Service.ServiceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username") // Store username in session
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String ten,
                        @RequestParam("password") String Mat_khau,
                        Model model) {
        if (userService.validateUser(ten, Mat_khau)) {
            model.addAttribute("username", ten); // Store username in session
            model.addAttribute("isLoggedIn", true); // Track login status
            return "index/trangChu"; // Redirect to home page
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
            return "index/dangNhap"; // Return to login page
        }
    }
}
