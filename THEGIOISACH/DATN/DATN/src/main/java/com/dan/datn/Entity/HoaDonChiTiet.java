//package com.dan.datn.Entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.List;
//
//@Entity
//@Table(name = "Hoa_Don_Chi_Tiet")
//@Getter
//@Setter
//public class HoaDonChiTiet {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="ID_hoa_don_chi_tiet")
//    private Long ID_Hoa_Don_Chi_Tiet;
//
//    // Quan hệ với bảng Thanh_Toan
//    @ManyToOne
//    @JoinColumn(name = "ID_thanh_toan", nullable = false)
//    private ThanhToan thanhToan;
//
//    // Quan hệ với bảng Admins
//    @ManyToOne
//    @JoinColumn(name = "ID_nguoi_dung", nullable = false)
//    private User user;
//
//    // Quan hệ với bảng San_Pham
//    @ManyToOne
//    @JoinColumn(name = "ID_san_pham", nullable = false)
//    private SanPham sanPham;
//
//    // Quan hệ với bảng Thong_Ke (OneToMany side)
//    @OneToMany(mappedBy = "hoaDonChiTiet")
//    private List<ThongKe> thongKeList;
//}
