package com.dan.datn.Service.ServiceImpl;

import com.dan.datn.Entity.HoaDon;
import com.dan.datn.Repository.HoaDonRepository;
import com.dan.datn.Service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HoaDonServiceImpl implements HoaDonService {
    private final HoaDonRepository hoaDonRepository;

    @Autowired
    public HoaDonServiceImpl(HoaDonRepository hoaDonRepository) {
        this.hoaDonRepository = hoaDonRepository;
    }

    @Override
    public List<HoaDon> getOrderDetailsByUsername(String username) {
        return hoaDonRepository.findByUsername(username);
    }
}
