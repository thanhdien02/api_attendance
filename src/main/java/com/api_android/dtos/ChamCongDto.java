package com.api_android.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChamCongDto {

    private String maNhanVien;

    private String ten;

    private Date ngayChamCong;
}
