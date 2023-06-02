package com.api_android.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "luong")
public class Luong {

    @Id
    private String id;

    private String maNhanVien;

    private String tenNhanVien;

    private int soNgayLamTrongThang;

    private int thang;

    private int nam;

    private Double tongLuong;

}
