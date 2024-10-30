package com.dan.datn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/admin")
public class homeAdminController {

    @GetMapping("/home")
    public String showHomeAdmin(Model model) {
        // Bạn có thể thêm dữ liệu vào model nếu cần thiết
        return "index/HomeAdmin";  // Điều này sẽ điều hướng đến `src/main/resources/templates/index/HomeAdmin.html`
    }
}
