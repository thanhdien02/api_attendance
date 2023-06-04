package com.api_android.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LuongNhanVienDTO {

    private Double luong;

    private String thang;

    private String nam;

    private Integer ngayNghi;

    private Integer ngayDiLam;

    private String maNhanVien;

    private String tenNhanVien;

}
