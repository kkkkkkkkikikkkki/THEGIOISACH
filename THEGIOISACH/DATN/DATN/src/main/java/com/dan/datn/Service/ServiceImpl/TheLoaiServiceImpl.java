package com.dan.datn.Service.ServiceImpl;

import com.dan.datn.Entity.TheLoai;
import com.dan.datn.Repository.TheLoaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheLoaiServiceImpl {

    @Autowired
    private TheLoaiRepository theLoaiRepository;

    public TheLoai findByCategoryName(String categoryName) {
        return theLoaiRepository.findByTheLoai(categoryName);
    }

    public TheLoai save(TheLoai theLoai) {
        return theLoaiRepository.save(theLoai);
    }

    public void saveTheLoai(TheLoai theLoai) {
    }
}
