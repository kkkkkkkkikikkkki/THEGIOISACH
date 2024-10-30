// NguoiBanRepository.java
package com.dan.datn.Repository;


import com.dan.datn.Entity.NguoiBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NguoiBanRepository extends JpaRepository<NguoiBan, Long> {
}
