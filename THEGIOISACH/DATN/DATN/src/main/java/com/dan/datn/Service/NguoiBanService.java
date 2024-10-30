// NguoiBanService.java
package com.dan.datn.Service;


import com.dan.datn.Entity.NguoiBan;

import java.util.List;

public interface NguoiBanService {
    List<NguoiBan> layTatCaNguoiBan();
    NguoiBan layNguoiBanTheoId(Long idNguoiBan);
}
