package com.dan.datn.Service.ServiceImpl;


import com.dan.datn.Entity.KhachHang;
import com.dan.datn.Repository.KhachHangRepository;
import com.dan.datn.Service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    private final KhachHangRepository khachHangRepository;

    @Autowired
    public KhachHangServiceImpl(KhachHangRepository khachHangRepository) {
        this.khachHangRepository = khachHangRepository;
    }

    @Override
    public List<KhachHang> layTatCaKhachHang() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang layKhachHangTheoId(Long idKhachHang) {
        return khachHangRepository.findById(idKhachHang).orElse(null);
    }
}
