package com.api_android.services.LuongNhanVien;

import com.api_android.entities.LuongNhanVien;

public interface LuongNhanVienService {
    LuongNhanVien tinhLuong(String maNhanVien, String thang, String nam);
}
