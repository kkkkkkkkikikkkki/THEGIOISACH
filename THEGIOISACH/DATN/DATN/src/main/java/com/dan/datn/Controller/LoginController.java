package com.dan.datn.Controller;

import com.dan.datn.Service.ServiceImpl.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public String login(@RequestParam("username") String ten,
                        @RequestParam("password") String matKhau,
                        Model model,
                        HttpSession session) {
        if (userServiceImpl.validateUser(ten, matKhau)) {
            session.setAttribute("username", ten);
            return "redirect:/trangchu";
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
            return "index/dangNhap";
        }
    }


    @PostMapping("/logouts")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        session.invalidate();

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        return "index/trangChu";
    }
}

