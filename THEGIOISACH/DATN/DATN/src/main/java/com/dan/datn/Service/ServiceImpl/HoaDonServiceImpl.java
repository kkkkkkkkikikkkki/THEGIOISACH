package com.dan.datn.Service.ServiceImpl;

import com.dan.datn.Entity.HoaDon;
import com.dan.datn.Repository.HoaDonRepository;
import com.dan.datn.Service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Override
    public HoaDon createHoaDon(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDon updateHoaDon(Integer id, HoaDon hoaDon) {
        Optional<HoaDon> existingHoaDon = hoaDonRepository.findById(id);
        if (existingHoaDon.isPresent()) {
            hoaDon.setId(id);
            return hoaDonRepository.save(hoaDon);
        }
        return null;
    }

    @Override
    public void deleteHoaDon(Integer id) {
        hoaDonRepository.deleteById(id);
    }

    @Override
    public HoaDon getHoaDonById(Integer id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    @Override
    public List<HoaDon> getAllHoaDons() {
        return hoaDonRepository.findAll();
    }


}
