package com.dan.datn.Service;

import com.dan.datn.Entity.ThanhToan;
import com.dan.datn.Entity.ThongKe;
import com.dan.datn.Repository.ThanhToanRepository;
import com.dan.datn.Repository.ThongKeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ThongKeService {

    @Autowired
    private ThongKeRepository thongKeRepository;

    @Autowired
    private ThanhToanRepository thanhToanRepository;

    public void calculateAndUpdateTotalRevenue() {
        // Lấy tất cả các bản ghi trong bảng ThongKe
        List<ThongKe> thongKes = thongKeRepository.findAll();

        // Nếu bảng ThongKe trống, không làm gì cả
        if (thongKes.isEmpty()) {
            System.out.println("Không có dữ liệu thống kê nào.");
            return;  // Không làm gì thêm nếu không có bản ghi
        }

        // Duyệt qua tất cả các thống kê và tính tổng doanh thu
        for (ThongKe thongKe : thongKes) {
            long totalRevenue = 0;

            // Kiểm tra nếu tongDoanhThu là null hoặc 0, sẽ gán giá trị mặc định là 0
            if (thongKe.getTongDoanhThu() == null || thongKe.getTongDoanhThu() == 0) {
                thongKe.setTongDoanhThu(0L); // Gán giá trị mặc định là 0
            }

            // Lấy tất cả các thanh toán trong thống kê này
            List<ThanhToan> thanhToans = thanhToanRepository.findAll();

            // Duyệt qua các thanh toán và cộng tổng doanh thu
            for (ThanhToan thanhToan : thanhToans) {
                totalRevenue += thanhToan.getTongTien(); // Tổng tiền của sản phẩm trong thanh toán
            }

            // Cập nhật tổng doanh thu vào ThongKe
            thongKe.setTongDoanhThu(totalRevenue);

            // Lưu lại thống kê đã cập nhật tổng doanh thu
            thongKeRepository.save(thongKe);
        }
    }

    // Phương thức lấy tất cả thống kê
    public List<ThongKe> getAllThongKe() {
        return thongKeRepository.findAll();
    }
}
