package com.dan.datn.Controller;

import com.dan.datn.Entity.User;
import com.dan.datn.Service.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DangKyController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/dangky")
    public String showDangKyForm(Model model) {
        model.addAttribute("user", new User()); // sử dụng Admin entity
        return "index/dangKy"; // trả về trang đăng ký
    }

    @PostMapping("/dangky")
    public String registerUser(@ModelAttribute("user") User user, Model model,
                               @RequestParam String ten,
                               @RequestParam String matKhau,
                               @RequestParam String diachi, // Thêm đây
                               @RequestParam String sdt,
                               @RequestParam String email) {
        if (userServiceImpl.isEmailExist(user.getEmail())) {
            model.addAttribute("error", "Email này đã được đăng ký."); // thông báo nếu email đã tồn tại
            return "index/DangKy"; // trả về trang đăng ký với thông báo lỗi
        } else {
            user = new User();
            user.setTen(ten);
            user.setMat_khau(matKhau);
            user.setDia_chi(diachi); // Gán giá trị địa chỉ
            user.setSDT(Integer.valueOf(sdt));
            user.setEmail(email);
            user.setRole(1);
            userServiceImpl.saveUser(user); // lưu admin mới vào cơ sở dữ liệu
            return "index/dangNhap"; // chuyển hướng đến trang đăng nhập
        }
    }
}
