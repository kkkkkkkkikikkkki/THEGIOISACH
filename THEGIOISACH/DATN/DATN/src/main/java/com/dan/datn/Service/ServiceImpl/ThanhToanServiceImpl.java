package com.dan.datn.Service.ServiceImpl;

import com.dan.datn.Entity.*;
import com.dan.datn.Repository.*;
import com.dan.datn.Service.ThanhToanService;
import com.dan.datn.dto.ProductDTO;
import com.dan.datn.dto.ThanhToanDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.dan.datn.Service.SanPhamService.sanPhamRepository;

@Service
@RequiredArgsConstructor
public class ThanhToanServiceImpl implements ThanhToanService {
    private final ThongTinMuaHangRepository thongTinMuaHangRepository;
    private final ThanhToanRepository thanhToanRepository;
    private final HoaDonChiTietRepository hoaDonChiTietRepository;
    private  final HoaDonRepository hoaDonRepository;
    private  final UserRepository  userRepository;
    @Override
    public ThanhToan createThanhToan(ThanhToanDTO request) {
        // Lưu thông tin mua hàng
        ThongTinMuaHang thongTinMuaHang = ThongTinMuaHang.builder()
                .tinh_tp(request.getTinh_tp())
                .Quan_huyen(request.getQuan_huyen())
                .Phuong_xa(request.getPhuong_xa())
                .soDienThoai(request.getSoDienThoai())
                .diaChiChiTiet(request.getDiaChiChiTiet())
                .build();
        thongTinMuaHangRepository.save(thongTinMuaHang);

        // Tạo đối tượng ThanhToan
        ThanhToan thanhToan = ThanhToan.builder()
                .phuongThucThanhToan(request.getPhuong_thuc_thanh_toan())
                .tongTien(request.getTongTien())
                .thongTinMuaHang(thongTinMuaHang)
                .build();

        // Lưu đối tượng ThanhToan vào cơ sở dữ liệu và kiểm tra null
        ThanhToan savedThanhToan = thanhToanRepository.save(thanhToan);
        if (savedThanhToan == null) {
            throw new RuntimeException("Lỗi khi lưu thanh toán.");
        }

        // Lưu các chi tiết hóa đơn
        List<HoaDonChiTiet> hoaDonChiTiets = new ArrayList<>();
        for (ProductDTO sanPhamDTO : request.getProducts()) {
            // Tìm sản phẩm theo ID
            SanPham sanPham = sanPhamRepository.findById(sanPhamDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại với ID: " + sanPhamDTO.getId()));

            // Tạo HoaDonChiTiet cho mỗi sản phẩm
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setSanPham(sanPham);
            hoaDonChiTiet.setTenSach(sanPham.getTenSach());
            hoaDonChiTiet.setSoLuong(sanPham.getSoLuongDaBan());
            hoaDonChiTiet.setGia(sanPham.getGia());  // Lấy giá từ sản phẩm
            hoaDonChiTiet.setThanhToan(savedThanhToan);  // Liên kết với thanh toán (ThanhToan)


            // Bạn cần đảm bảo gán giá trị cho hoaDon (HoaDon)
            HoaDon hoaDon = new HoaDon();  // Giả sử bạn tạo đối tượng HoaDon ở đây
            hoaDon.setSDT(request.getSoDienThoai());
            hoaDon.setTen("Khai");
            hoaDon.setTrangThaiThanhToan("Thành Công");
            hoaDon.setDiaChi(request.getDiaChiChiTiet());
            hoaDon.setNgayDatHang(new Date(2024 , 11, 15));
            hoaDon.setHoaDonChiTietList(hoaDonChiTiets);
            hoaDon.setTongTien(request.getTongTien());
            hoaDon.setThanhToan(savedThanhToan);  // Thiết lập thanh toán cho hóa đơn
            Optional<User> userOptional = userRepository.findById(1L);
            if (userOptional.isPresent()) {
                User user = userOptional.get();  // Lấy đối tượng User từ Optional
                hoaDon.setAdmins(user);  // Gán đối tượng User vào thuộc tính admins của HoaDon
            } else {
                throw new RuntimeException("Người dùng không tồn tại với ID: 1");
            }
            hoaDonRepository.save(hoaDon);  // Lưu hóa đơn vào cơ sở dữ liệu

            hoaDonChiTiet.setHoaDon(hoaDon);  // Gán đối tượng HoaDon cho HoaDonChiTiet

            // Giảm số lượng tồn kho của sản phẩm sau khi thanh toán
            sanPham.setSoLuongTonKho(sanPham.getSoLuongTonKho() - sanPham.getSoLuongDaBan());
            sanPhamRepository.save(sanPham);

            hoaDonChiTiets.add(hoaDonChiTiet);
        }

        // Lưu tất cả HoaDonChiTiet cùng lúc
        hoaDonChiTietRepository.saveAll(hoaDonChiTiets);

        return savedThanhToan;
    }


    //    public ThanhToan createThanhToan(ThanhToanDTO request) {
//        ThongTinMuaHang thongTinMuaHang = ThongTinMuaHang.builder()
//                .tinh_tp(request.getTinh_tp())
//                .Quan_huyen(request.getQuan_huyen())
//                .Phuong_xa(request.getPhuong_xa())
//                .soDienThoai(request.getSoDienThoai())
//                .diaChiChiTiet(request.getDiaChiChiTiet())
//                .build();
//        thongTinMuaHangRepository.save(thongTinMuaHang);
//        ThanhToan thanhToan = ThanhToan.builder()
//                .phuongThucThanhToan(request.getPhuong_thuc_thanh_toan())
//                .tongTien(request.getTongTien())
//                .thongTinMuaHang(thongTinMuaHang)
//                .build();
//        List<SanPham> sanPhams = new ArrayList<>();
//        for (ProductDTO sanPhamDTO : request.getProducts()) {
//            SanPham sanPham = sanPhamRepository.findById(sanPhamDTO.getId())
//                    .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại với ID: " + sanPhamDTO.getId()));
//            sanPhams.add(sanPham);
//        }
//        thanhToan.setSanPhams(sanPhams);
//        return thanhToanRepository.save(thanhToan);
//    }
    @Override
    public List<HoaDonChiTiet> getAllThanhToans() {
        return hoaDonChiTietRepository.findAll();
    }
}
