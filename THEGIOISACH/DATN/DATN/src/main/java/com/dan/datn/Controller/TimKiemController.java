package com.dan.datn.Controller;

import com.dan.datn.Entity.SanPham;
import com.dan.datn.Service.ServiceImpl.SanPhamService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TimKiemController {

    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/search")
    public String searchSanPham(@RequestParam("keyword") String keyword, Model model) {
        List<SanPham> sanPhams = sanPhamService.searchAllFields(keyword);
        model.addAttribute("sanPhams", sanPhams);
        String username = (String) httpSession.getAttribute("username");
        model.addAttribute("username", username);
        return "index/sanPhamSauTimKiem"; // Trang hiển thị kết quả tìm kiếm
    }

    @GetMapping("/suggestions")
    @ResponseBody
    public List<String> getSuggestions(@RequestParam("keyword") String keyword) {
        return sanPhamService.findSuggestions(keyword);
    }

}
