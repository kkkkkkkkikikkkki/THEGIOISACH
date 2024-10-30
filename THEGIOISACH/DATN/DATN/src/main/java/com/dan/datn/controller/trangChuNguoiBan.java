package com.dan.datn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class trangChuNguoiBan {

 @GetMapping("/trangchunguoiban")
    public String giohang(Model model) {return "index/trangChuNguoiBan";}

}
