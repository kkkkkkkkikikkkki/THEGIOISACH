package com.dan.datn.Service;

import com.dan.datn.Repository.HinhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HinhService {

    @Autowired
    private HinhRepository hinhRepository;

    // Method to get the image object by its ID
    public Object getHinhById(Long id) {
        return hinhRepository.findById(id).orElse(null);
    }
}
