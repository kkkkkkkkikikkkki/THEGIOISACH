package com.dan.datn.Controller;
import com.dan.datn.Service.ServiceImpl.EmailService;
import com.dan.datn.Service.ServiceImpl.NguoiDungServiceImpl;
import com.dan.datn.dto.QuenMatKhauDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class QuenMatKhauController {
    @Autowired
    private NguoiDungServiceImpl nguoiDungService;

    @Autowired
    private EmailService emailService;

    /**
     * Gửi email đặt lại mật khẩu
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<String> sendResetPasswordEmail(@RequestBody QuenMatKhauDTO request) {
        String email = request.getEmail();

        // Kiểm tra email có tồn tại trong hệ thống
        if (!nguoiDungService.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("Email không tồn tại trong hệ thống!");
        }

        // Tạo token đặt lại mật khẩu
        String token = UUID.randomUUID().toString();
        nguoiDungService.savePasswordResetToken(email, token);

        // Gửi email đặt lại mật khẩu
        String resetLink = "http://localhost:8080/api/auth/reset-password?token=" + token;
        emailService.sendEmail(email, "Đặt lại mật khẩu", "Click vào link sau để đặt lại mật khẩu: " + resetLink);

        return ResponseEntity.ok("Email đặt lại mật khẩu đã được gửi!");
    }

    /**
     * Đặt lại mật khẩu bằng token
     */
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam("token") String token, @RequestBody QuenMatKhauDTO request) {
        String newPassword = request.getNewPassword();

        // Kiểm tra token có hợp lệ hay không
        String email = nguoiDungService.getEmailByResetToken(token);
        if (email == null) {
            return ResponseEntity.badRequest().body("Token không hợp lệ hoặc đã hết hạn!");
        }

        // Cập nhật mật khẩu mới
        nguoiDungService.updatePassword(email, newPassword);

        // Xóa token sau khi sử dụng
        nguoiDungService.deleteResetToken(token);

        return ResponseEntity.ok("Mật khẩu đã được đặt lại thành công!");
    }
}
