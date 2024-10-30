package com.dan.datn.Service.ServiceImpl;


import com.dan.datn.Entity.NguoiBan;
import com.dan.datn.Repository.NguoiBanRepository;
import com.dan.datn.Service.NguoiBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NguoiBanServiceImpl implements NguoiBanService {

    private final NguoiBanRepository nguoiBanRepository;

    @Autowired
    public NguoiBanServiceImpl(NguoiBanRepository nguoiBanRepository) {
        this.nguoiBanRepository = nguoiBanRepository;
    }

    @Override
    public List<NguoiBan> layTatCaNguoiBan() {
        return nguoiBanRepository.findAll();
    }

    @Override
    public NguoiBan layNguoiBanTheoId(Long idNguoiBan) {
        return nguoiBanRepository.findById(idNguoiBan).orElse(null);
    }
}
