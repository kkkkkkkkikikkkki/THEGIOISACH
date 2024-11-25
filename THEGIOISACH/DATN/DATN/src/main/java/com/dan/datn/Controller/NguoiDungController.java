package com.dan.datn.Controller;

import com.dan.datn.Entity.User;
import com.dan.datn.Repository.UserRepository;
import com.dan.datn.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nguoidung")
public class NguoiDungController {

    @Autowired
    private UserService nguoiDungService;
    @Autowired
    private UserRepository userRepository;

    // API để lấy danh sách người dùng
    @GetMapping
    public List<User> getAllNguoiDung() {
        return nguoiDungService.getAllNguoiDung();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> xoaNguoiDung(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Trả về HTTP 204 nếu xóa thành công
        }
        return ResponseEntity.notFound().build(); // Trả về HTTP 404 nếu không tìm thấy người dùng
    }
}
