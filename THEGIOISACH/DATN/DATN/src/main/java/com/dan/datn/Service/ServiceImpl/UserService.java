package com.dan.datn.Service.ServiceImpl;

import com.dan.datn.Entity.User;
import com.dan.datn.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository; // Khai báo đúng đối tượng userRepository

    public boolean validateUser(String ten, String Mat_khau) {
        Optional<User> userOptional = userRepository.findByTen(ten);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getMat_khau().equals(Mat_khau); // So sánh mật khẩu
        }
        return false;
    }

    // Kiểm tra xem email đã tồn tại chưa
    public boolean isEmailExist(String email) {
        User user = userRepository.findByEmail(email); // Gọi phương thức thông qua userRepository
        return user != null; // Nếu user không null, nghĩa là email đã tồn tại
    }

    // Lưu người dùng mới
    public void saveUser(User user) {
        // Setting the role directly to 1
        user.setRole(1); // Ensure role is set as an integer
        userRepository.save(user);
    }
}
