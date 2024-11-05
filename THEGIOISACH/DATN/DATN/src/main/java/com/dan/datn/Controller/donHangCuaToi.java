package com.dan.datn.Controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class donHangCuaToi {
    @GetMapping("/donhangcuatoi")
    public String donhangcuatoi() {return "layout/Donhangcuatoi";}
}
