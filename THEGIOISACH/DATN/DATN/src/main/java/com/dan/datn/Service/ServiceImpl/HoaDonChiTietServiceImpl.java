package com.dan.datn.Service.ServiceImpl;

import com.dan.datn.Entity.HoaDonChiTiet;
import com.dan.datn.Service.HoaDonChiTietService;
import com.dan.datn.Repository.HoaDonChiTietRepository;
import com.dan.datn.Service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;



    @Override
    public HoaDonChiTiet createHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public HoaDonChiTiet updateHoaDonChiTiet(Integer id, HoaDonChiTiet hoaDonChiTiet) {
        return null;
    }

//    @Override
//    public HoaDonChiTiet updateHoaDonChiTiet(Integer id, HoaDonChiTiet hoaDonChiTiet) {
//        Optional<HoaDonChiTiet> existingHoaDonChiTiet = hoaDonChiTietRepository.findById(id);
//        if (existingHoaDonChiTiet.isPresent()) {
//            hoaDonChiTiet.setId(id);
//            return hoaDonChiTietRepository.save(hoaDonChiTiet);
//        }
//        return null;
//    }

    @Override
    public void deleteHoaDonChiTiet(Integer id) {
        hoaDonChiTietRepository.deleteById(id);
    }

    @Override
    public HoaDonChiTiet getHoaDonChiTietById(Integer id) {
        return hoaDonChiTietRepository.findById(id).orElse(null);
    }

    @Override
    public List<HoaDonChiTiet> getAllHoaDonChiTiets() {
        return hoaDonChiTietRepository.findAll();
    }


}
