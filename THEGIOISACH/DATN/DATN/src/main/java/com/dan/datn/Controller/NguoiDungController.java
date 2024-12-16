package com.dan.datn.Controller;

import com.dan.datn.Entity.User;
import com.dan.datn.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NguoiDungController {

    @Autowired
    private UserService nguoiDungService;

    // API để lấy danh sách người dùng
    @GetMapping("/api/nguoidung")
    public List<User> getAllNguoiDung() {
        return nguoiDungService.getAllNguoiDung();
    }
    // API để lấy thông tin người dùng theo ID
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getNguoiDungById(@PathVariable Long id) {
//        Optional<User> user = nguoiDungService.getUserById(id);
//        if (user.isPresent()) {
//            return ResponseEntity.ok(user.get());
//        }
//        return ResponseEntity.notFound().build(); // Trả về HTTP 404 nếu không tìm thấy người dùng
//    }

    // API để xóa người dùng
    @DeleteMapping("/api/nguoidung/{id}")
    public ResponseEntity<Void> xoaNguoiDung(@PathVariable Long id) {
        if (nguoiDungService.deleteNguoiDung(id)) {
            return ResponseEntity.noContent().build(); // Trả về HTTP 204 nếu xóa thành công
        }
        return ResponseEntity.notFound().build(); // Trả về HTTP 404 nếu không tìm thấy người dùng
    }
}
