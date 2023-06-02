package com.api_android.services.Luong;

import com.api_android.dtos.ChamCongDto;
import com.api_android.dtos.LuongDto;
import com.api_android.entities.ChamCong;
import com.api_android.entities.Luong;

import java.util.List;

public interface LuongService {

    Boolean getByMaThang(String manv, int thang, int nam);
    Luong getLuong(String id);

    Luong create (LuongDto dto);

    Luong update (String id, LuongDto dto);

    Luong delete (String id);

    List<Luong> getAll();
}
