package com.api_android;

import com.api_android.entities.TaiKhoan;
import com.api_android.repositories.TaiKhoanRepository;
import com.api_android.utils.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ApiAndroidApplication implements CommandLineRunner {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    public static void main(String[] args) {
        SpringApplication.run(ApiAndroidApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(taiKhoanRepository.count()==0){
            TaiKhoan taiKhoan = new TaiKhoan("ma01","diennt","20110458@student.hcmute.edu.vn","123456789",
                    Arrays.asList(EnumRole.ROLE_ADMIN.name()));
            taiKhoanRepository.save(taiKhoan);
        }
    }
}
