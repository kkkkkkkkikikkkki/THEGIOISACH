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

import java.util.List;

@Controller
public class DangKyADController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/taomoi")
    public String taomoi(Model model) {
        model.addAttribute("user", new User()); // Đối tượng để tạo mới admin
        List<User> adminList = userServiceImpl.getAllAdmins(); // Lấy danh sách admin hiện có
        model.addAttribute("adminList", adminList);
        return "layoutAdmin/TaoMoi";
    }

    @PostMapping("/dangkyadmin")
    public String registerAdmin(@ModelAttribute("user") User user, Model model,
                                @RequestParam String ten,
                                @RequestParam String matKhau,
                                @RequestParam String diachi,
                                @RequestParam String sdt,
                                @RequestParam String email) {
        if (userServiceImpl.isEmailExist(email)) {
            model.addAttribute("error", "Email này đã được đăng ký.");
            return "layoutAdmin/TaoMoi";
        } else {
            user.setTen(ten);
            user.setMat_khau(matKhau);
            user.setDia_chi(diachi);
            user.setSDT(Integer.parseInt(sdt));
            user.setEmail(email);
            user.setRole(0); // Đặt role là 0 cho admin
            userServiceImpl.saveUser(user);
            return "redirect:/loginadmin";
        }
    }
}
