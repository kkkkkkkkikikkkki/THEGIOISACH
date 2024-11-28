    package com.dan.datn.Service.ServiceImpl;

    import com.dan.datn.Entity.DanhGia;
    import com.dan.datn.Entity.SanPham;
    import com.dan.datn.Repository.DanhGiaRepository;
    import com.dan.datn.Service.DanhGiaService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public class DanhGiaServiceImpl implements DanhGiaService {

        @Autowired
        private DanhGiaRepository danhGiaRepository;

        @Override
        public List<DanhGia> getDanhGiaBySanPham(SanPham sanPham) {
            return danhGiaRepository.findBySanPham(sanPham);
        }

        @Override
        public void save(DanhGia danhGia) {
            danhGiaRepository.save(danhGia);
        }
    }

