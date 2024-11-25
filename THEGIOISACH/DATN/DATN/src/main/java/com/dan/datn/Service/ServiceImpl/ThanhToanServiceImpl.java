package com.dan.datn.Service.ServiceImpl;

import com.dan.datn.Entity.SanPham;
import com.dan.datn.Entity.ThanhToan;
import com.dan.datn.Repository.SanPhamRepository;
import com.dan.datn.Repository.ThanhToanRepository;
import com.dan.datn.Repository.UserRepository;
import com.dan.datn.Service.ThanhToanService;
import com.dan.datn.dto.ProductDTO;
import com.dan.datn.dto.ThanhToanDTO;
import com.dan.datn.Event.OnRegistrationCompleteEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ThanhToanServiceImpl implements ThanhToanService {

    private final ThanhToanRepository thanhToanRepository;
    private final SanPhamRepository sanPhamRepository;
    private final UserRepository userRepository;
    private final Random random = new Random();
    private final ApplicationEventPublisher eventPublisher;
    private Long generationId() {
        return random.nextLong(1000);
    }

    @Transactional
    public void thanhToan(ThanhToanDTO request) {
        // Kiểm tra user
        var user = userRepository.findByTen(request.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kiểm tra danh sách sản phẩm
        if (request.getProducts() == null || request.getProducts().isEmpty()) {
            throw new IllegalArgumentException("No products in checkout request");
        }

        double tongTienDonHang = 0;
        List<ThanhToan> thanhToans = new ArrayList<>();

        for (ProductDTO productDTO : request.getProducts()) {
            // Kiểm tra và lấy thông tin sản phẩm
            var sanPham = sanPhamRepository.findById(productDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + productDTO.getId()));

            // Kiểm tra số lượng tồn kho
            if (sanPham.getSoLuongTonKho() < productDTO.getSoLuong()) {
                throw new RuntimeException("Không đủ số lượng sản phẩm " + sanPham.getTenSach() +
                        " trong kho. Chỉ còn " + sanPham.getSoLuongTonKho() + " sản phẩm.");
            }
            // Kiểm tra số lượng tổng sản phẩm
            if (sanPham.getSoLuongTongSanPham() < productDTO.getSoLuong()) {
                throw new RuntimeException("Không đủ số lượng sản phẩm " + sanPham.getTenSach() +
                        " trong kho. Chỉ còn " + sanPham.getSoLuongTongSanPham() + " sản phẩm.");
            }

            // Trừ số lượng tồn kho
            sanPham.setSoLuongTonKho(sanPham.getSoLuongTonKho() - productDTO.getSoLuong());
            // Trừ số lượng tổng sản phẩm
            sanPham.setSoLuongTonKho(sanPham.getSoLuongTongSanPham() - productDTO.getSoLuong());
            // Lưu cập nhật số lượng tồn kho
            sanPhamRepository.save(sanPham);
            // Tính tổng tiền cho sản phẩm này
            double tongTienSanPham = productDTO.getGia() * productDTO.getSoLuong();
            tongTienDonHang += tongTienSanPham;

            // Tạo đối tượng thanh toán cho từng sản phẩm
            ThanhToan thanhToan = ThanhToan.builder()
                    .ID_thanh_toan(generationId())
                    .phuongThucThanhToan(request.getPhuong_thuc_thanh_toan())
                    .soLuong(productDTO.getSoLuong())
                    .tongTien(tongTienSanPham) // Tổng tiền cho từng sản phẩm
                    .user(user)
                    .sanPham(sanPham)
                    .ngayDatHang(new Date())
                    .build();
            thanhToans.add(thanhToan);
        }

        try {
            // Lưu tất cả các đơn thanh toán
            thanhToanRepository.saveAll(thanhToans);
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user));
        } catch (Exception e) {
            // Nếu có lỗi, throw exception để rollback transaction
            throw new RuntimeException("Lỗi khi xử lý thanh toán", e);
        }
    }
}
