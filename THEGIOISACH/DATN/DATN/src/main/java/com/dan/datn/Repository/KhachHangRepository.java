// KhachHangRepository.java
package com.dan.datn.Repository;


import com.dan.datn.Entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {
}
