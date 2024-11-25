package com.dan.datn.Service;

import com.dan.datn.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean validateUser(String ten, String matKhau, String sdt); // Xác thực người dùng dựa trên tên và mật khẩu

    // Xác thực người dùng bằng cách kiểm tra tên và mật khẩu
    boolean validateUser(String ten, String matKhau);

    boolean validateAdmin(String ten, String matKhau);

    boolean isEmailExist(String email); // Kiểm tra sự tồn tại của email
    void saveUser(User user); // Lưu thông tin người dùng mới

    void saveAdmin(User user);

    Optional<User> getUserByTen(String ten); // Lấy người dùng dựa vào tên
    List<User> getAllNguoiDung();


}
