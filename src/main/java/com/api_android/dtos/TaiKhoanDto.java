package com.api_android.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaiKhoanDto {
    private String maNhanVien;

    private String name;

    // email không được trùng nhau
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY, value = "password")
    private String password;

    private String dienThoai;

    private Double luong;

    private List<String> roles = new ArrayList<>();
}
