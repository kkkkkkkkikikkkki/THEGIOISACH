package com.dan.datn.Service.ServiceImpl;

import com.dan.datn.Entity.User;
import com.dan.datn.Repository.UserRepository;
import com.dan.datn.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository; // Khai báo UserRepository để thực hiện truy vấn

    // Xác thực người dùng bằng cách kiểm tra tên và mật khẩu
    @Override
    public boolean validateUser(String ten, String matKhau) {
        Optional<User> userOptional = userRepository.findByTen(ten);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getMat_khau().equals(matKhau); // So sánh mật khẩu
        }
        return false;
    }

    // Kiểm tra xem email đã tồn tại trong hệ thống chưa
    @Override
    public boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email); // Sử dụng phương thức existsByEmail để kiểm tra
    }

    // Lưu người dùng mới vào cơ sở dữ liệu
    @Override
    public void saveUser(User user) {
        user.setRole(1); // Đặt giá trị role mặc định là 1
        userRepository.save(user);
    }

    // Lấy thông tin người dùng dựa vào tên
    @Override
    public Optional<User> getUserByTen(String ten) {
        return userRepository.findByTen(ten);
    }
}
