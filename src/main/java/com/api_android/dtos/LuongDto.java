package com.api_android.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LuongDto {

    private String maNhanVien;

    private String tenNhanVien;

    private int soNgayLamTrongThang;

    private int thang;

    private int nam;

    private Double tongLuong;
}
