package com.api_android.services.TaiKhoan;


import com.api_android.dtos.TaiKhoanDto;
import com.api_android.entities.TaiKhoan;

import java.security.Principal;
import java.util.List;

public interface TaiKhoanService {
    TaiKhoan getTaiKhoan(String id);
    TaiKhoan create (TaiKhoanDto dto, Principal principal);

    TaiKhoan update (String id, TaiKhoanDto dto);

    TaiKhoan delete (String id);
    List<TaiKhoan> getAll();
}
