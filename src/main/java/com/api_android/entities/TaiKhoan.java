package com.api_android.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tai-khoan")
public class TaiKhoan {
    @Id
    private String id;

    private String maNhanVien;
    private String name;

    // email không được trùng nhau
    private String email;
    private String password;

    private String dienThoai;

    private Double luong;
<<<<<<< HEAD

=======
>>>>>>> bac6b745cfd253a3144de5dfa6c6dd9f6557cc90
    private List<String> roles = new ArrayList<>();

    private boolean trangThai = true;

    public TaiKhoan(String maNhanVien, String name, String email, String password, List<String> roles) {
        this.maNhanVien = maNhanVien;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
