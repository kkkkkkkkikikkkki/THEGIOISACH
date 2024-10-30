package com.dan.datn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class trangcanhan {

    @GetMapping("/trangcanhan")
    public String giohang(Model model) {return "index/trangCaNhan";}

}
