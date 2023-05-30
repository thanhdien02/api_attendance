package com.api_android.services.LuongNhanVien;

import com.api_android.entities.ChamCong;
import com.api_android.entities.LuongNhanVien;
import com.api_android.entities.TaiKhoan;
import com.api_android.exceptions.InvalidException;
import com.api_android.exceptions.NotFoundException;
import com.api_android.repositories.ChamCongRepository;
import com.api_android.repositories.LuongNhanVienRepository;
import com.api_android.repositories.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

public class LuongNhanVienServiceImpl implements LuongNhanVienService{

    @Autowired
    LuongNhanVienRepository luongNhanVienRepository;
    @Autowired
    ChamCongRepository chamCongRepository;
    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    @Override
    public LuongNhanVien tinhLuong(String maNhanVien, String thang, String nam) {
        int month = Integer.parseInt(thang);
        int year = Integer.parseInt(nam);
        int soNgayCong;
        int soNgayTrongThang;
        int soNgayNghi;
        double luongCung;
        double luongTrongThang;

        YearMonth yearMonth = YearMonth.of(year,month);
        soNgayTrongThang = yearMonth.lengthOfMonth();

        TaiKhoan nhanVien = taiKhoanRepository.findById(maNhanVien).
                orElseThrow(() -> new NotFoundException(String
                        .format("Tài khoản có id %s không tồn tại", maNhanVien)));

        Pattern regexPattern = Pattern.compile(String.format("^(\\d{2}/%02d/%04d \\d{2}:\\d{2}:\\d{2})$", month, year));
        List<ChamCong> chamCongList = chamCongRepository.findByNgayChamCongRegex(regexPattern);
        soNgayCong = chamCongList.size();

        soNgayNghi = soNgayTrongThang - soNgayCong;
        luongCung = nhanVien.getLuong();
        luongTrongThang =  luongCung - ((luongCung/soNgayTrongThang) );

        return null;
    }
}
