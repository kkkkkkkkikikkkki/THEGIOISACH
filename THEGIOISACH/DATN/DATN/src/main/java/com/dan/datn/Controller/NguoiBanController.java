package com.dan.datn.Controller;



import com.dan.datn.Service.NguoiBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nguoiban")
public class NguoiBanController {

    private final NguoiBanService nguoiBanService;

    @Autowired
    public NguoiBanController(NguoiBanService nguoiBanService) {
        this.nguoiBanService = nguoiBanService;
    }

    @GetMapping
    public List<NguoiBan> layTatCaNguoiBan() {
        return nguoiBanService.layTatCaNguoiBan();
    }

    @GetMapping("/{id}")
    public NguoiBan layNguoiBanTheoId(@PathVariable Long idKhachHang) {
        return nguoiBanService.layNguoiBanTheoId(idKhachHang);
    }
}
