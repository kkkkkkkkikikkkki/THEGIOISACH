package com.dan.datn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class trangChuNguoiBan {

 @GetMapping("/trangchunguoiban")
    public String giohang(Model model) {return "index/trangChuNguoiBan";}

}
