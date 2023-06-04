package com.api_android.services.LuongNhanVien;

import com.api_android.dtos.LuongNhanVienDTO;
import com.api_android.entities.LuongNhanVien;

import java.util.List;

public interface LuongNhanVienService {
    List<LuongNhanVien> getALL();
    LuongNhanVien tinhLuong(LuongNhanVienDTO dto);

}
