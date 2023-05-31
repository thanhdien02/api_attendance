package com.api_android.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "luong-nhan-vien")
public class LuongNhanVien {
    @Id
    private String id;

    private Double luong;

    private String thang;

    private String nam;

    private Integer ngayNghi;

    private String maNhanVien;
}
