package com.dan.datn.Service;

import com.dan.datn.Entity.User;

import java.util.Optional;

public interface UserService {
    boolean validateUser(String ten, String matKhau); // Xác thực người dùng dựa trên tên và mật khẩu
    boolean isEmailExist(String email); // Kiểm tra sự tồn tại của email
    void saveUser(User user); // Lưu thông tin người dùng mới
    Optional<User> getUserByTen(String ten); // Lấy người dùng dựa vào tên
}
