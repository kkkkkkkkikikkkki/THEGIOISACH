package com.dan.datn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class trangCaNhanController {
    @GetMapping("/trangcanhan")
    public String trangCaNhan(Model model) {
        return "index/trangCaNhan";
    }
}
