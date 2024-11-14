package com.dan.datn.Controller;

import com.dan.datn.Service.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/loginadmin")
    public String loginAD(@RequestParam("username") String ten,
                        @RequestParam("password") String matKhau,
                          @RequestParam("phonenumber") String sdt,
                        Model model,
                        HttpSession session) {
        if (userServiceImpl.validateUser(ten, matKhau, sdt)) {
            session.setAttribute("username", ten);
            return "redirect:/HomeAdmin";
        } else {
            model.addAttribute("error", "Tên đăng nhập, số điện thoại hoặc mật khẩu không đúng");
            return "index/LoginAdmin";
        }
    }
}

