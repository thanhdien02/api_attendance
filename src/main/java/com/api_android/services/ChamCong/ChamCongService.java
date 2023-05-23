package com.api_android.services.ChamCong;

import com.api_android.dtos.ChamCongDto;
import com.api_android.entities.ChamCong;

import java.util.List;

public interface ChamCongService {

    ChamCong getChamCong(String id);

    ChamCong create (ChamCongDto dto);

    ChamCong update (String id, ChamCongDto dto);

    ChamCong delete (String id);

    List<ChamCong> getAll();
}
