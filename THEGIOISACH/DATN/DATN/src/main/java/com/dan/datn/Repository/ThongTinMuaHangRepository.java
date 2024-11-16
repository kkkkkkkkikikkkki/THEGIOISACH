package com.dan.datn.Repository;

import com.dan.datn.Entity.ThongTinMuaHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThongTinMuaHangRepository extends JpaRepository<ThongTinMuaHang, Long> {
}
