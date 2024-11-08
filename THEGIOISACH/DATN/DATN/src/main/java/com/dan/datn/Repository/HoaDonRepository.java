package com.dan.datn.Repository;


import com.dan.datn.Entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {

    @Query("SELECT hd FROM HoaDon hd " +
            "JOIN hd.admins a " +
            "JOIN hd.hoaDonChiTietList hdct " +
            "JOIN hdct.sanPham sp " +
            "JOIN sp.hinh h " +
            "WHERE a.ten = :username")
    List<HoaDon> findByUsername(String username);
}
