package com.dan.datn.Controller;

import com.dan.datn.Entity.User;
import com.dan.datn.Service.ServiceImpl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@SessionAttributes("username")
@Controller
public class TrangCaNhanController {
    private final UserServiceImpl userServiceImpl;

    public TrangCaNhanController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/trangcanhan")
    public String trangCaNhan(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            model.addAttribute("error", "Bạn vui lòng đăng nhập trước khi truy cập trang cá nhân.");
            return "index/dangNhap";
        }

        model.addAttribute("username", username);
        return "index/trangCaNhan";
    }

    @GetMapping("/thongtintaikhoan")
    public String getThongTinCaNhan(HttpSession session, Model model) {
//        String username = (String) session.getAttribute("username");
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);

        if (username == null) {
            model.addAttribute("error", "Bạn vui lòng đăng nhập trước khi truy cập thông tin cá nhân.");
            return "index/dangNhap";
        }

        Optional<User> userOptional = userServiceImpl.getUserByTen(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
        } else {
            model.addAttribute("error", "Người dùng không tồn tại.");
        }

        return "layout/Thongtincanhan";
    }



    // New method for updating user information
    @PostMapping("/updateUser")
    public String updateUser(
            @RequestParam("ten") String ten,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("diachi") String diachi,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            redirectAttributes.addFlashAttribute("error", "Bạn vui lòng đăng nhập trước khi cập nhật thông tin cá nhân.");
            return "redirect:/login";
        }

        Optional<User> userOptional = userServiceImpl.getUserByTen(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setTen(ten);
            user.setSDT(Integer.valueOf(phone));
            user.setEmail(email);
            user.setDia_chi(diachi);

            try {
                userServiceImpl.saveUser(user);
                redirectAttributes.addFlashAttribute("success", "Cập nhật thông tin thành công!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Đã xảy ra lỗi khi cập nhật thông tin.");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Người dùng không tồn tại.");
        }

        return "redirect:/thongtintaikhoan";
    }



    @PostMapping("/updatePassword")
    public String updatePassword(
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        // Lấy thông tin username từ session
        String username = (String) session.getAttribute("username");

        // Kiểm tra xem username có tồn tại trong session không
        if (username == null) {
            redirectAttributes.addFlashAttribute("error", "Bạn vui lòng đăng nhập trước khi cập nhật mật khẩu.");
            return "redirect:/login";
        }

        // Kiểm tra xem có trường nhập liệu nào bị bỏ trống không
        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Vui longf nhập thông tin cần thiết.");
            return "redirect:/thongtintaikhoan";
        }

        if (oldPassword.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Mật khẩu cũ không được để trống.");
            return "redirect:/thongtintaikhoan";
        }
        if ( confirmPassword.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không được để trống bất kỳ trường nào.");
            return "redirect:/thongtintaikhoan";
        }
        if (newPassword.isEmpty() ) {
            redirectAttributes.addFlashAttribute("error", "Mật khẩu mới không được để trống");
            return "redirect:/thongtintaikhoan";
        }

        // Gọi phương thức trong service để xử lý việc cập nhật mật khẩu
        boolean success = userServiceImpl.updatePassword(username, oldPassword, newPassword, confirmPassword);

        if (!success) {
            // Kiểm tra điều kiện thất bại về mật khẩu cũ hoặc mật khẩu mới không trùng khớp
            Optional<User> userOptional = userServiceImpl.getUserByTen(username);
            if (userOptional.isPresent() && !userOptional.get().getMat_khau().equals(oldPassword)) {
                redirectAttributes.addFlashAttribute("error", "Mật khẩu cũ không chính xác.");
            } else {
                redirectAttributes.addFlashAttribute("error", "Mật khẩu mới và xác nhận mật khẩu không trùng nhau.");
            }
            return "redirect:/thongtintaikhoan";
        }

        redirectAttributes.addFlashAttribute("success", "Cập nhật mật khẩu thành công!");
        return "redirect:/thongtintaikhoan";
    }


}
