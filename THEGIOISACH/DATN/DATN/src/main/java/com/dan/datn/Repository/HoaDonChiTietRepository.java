package com.dan.datn.Repository;

import com.dan.datn.Entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Integer> {

    @Query("SELECT hdct FROM HoaDonChiTiet hdct " +
            "JOIN hdct.hoaDon hd " +
            "JOIN hd.admins nd " +
            "WHERE nd.ten = :username")
    List<HoaDonChiTiet> findChiTietHoaDonByUsername(@Param("username") String username);
}
