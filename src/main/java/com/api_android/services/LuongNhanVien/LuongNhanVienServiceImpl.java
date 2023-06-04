package com.api_android.services.LuongNhanVien;

import com.api_android.dtos.LuongNhanVienDTO;
import com.api_android.entities.ChamCong;
import com.api_android.entities.Luong;
import com.api_android.entities.LuongNhanVien;
import com.api_android.entities.TaiKhoan;
import com.api_android.exceptions.InvalidException;
import com.api_android.exceptions.NotFoundException;
import com.api_android.repositories.ChamCongRepository;
import com.api_android.repositories.LuongNhanVienRepository;
import com.api_android.repositories.TaiKhoanRepository;
import org.apache.jasper.tagplugins.jstl.core.If;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class LuongNhanVienServiceImpl implements LuongNhanVienService{

    @Autowired
    LuongNhanVienRepository luongNhanVienRepository;
    @Autowired
    ChamCongRepository chamCongRepository;
    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    private double tinhLuongNhanVien(double luong, int soNgayTrongThang, int soNgayCong){
        double luongNV = (luong/soNgayTrongThang) * soNgayCong;
        double roundedNumber = Math.round(luongNV * 1000.0) / 1000.0;
        return roundedNumber;
    }

    @Override
    public List<LuongNhanVien> getALL() {
        return luongNhanVienRepository.findAll();
    }

    @Override
    public LuongNhanVien tinhLuong(LuongNhanVienDTO dto) {
        String DateFormatter = dto.getThang()+"/"+dto.getNam();
        int month = Integer.parseInt(dto.getThang());
        int year = Integer.parseInt(dto.getNam());
        int soNgayCong=0;
        int soNgayTrongThang=0;
        double luong = 0;

        TaiKhoan taiKhoan = taiKhoanRepository.findByMaNhanVien(dto.getMaNhanVien());
        if (taiKhoan == null) {
            throw new NotFoundException(String.format("Tài khoản có maNhanVien %s không tồn tại", dto.getMaNhanVien()));
        }

        List<ChamCong> chamCongs = chamCongRepository.findByMaNhanVien(dto.getMaNhanVien())
                .stream()
                .filter(chamCong -> chamCong.getNgayChamCong().contains(DateFormatter))
                .collect(Collectors.toList());

        YearMonth yearMonth = YearMonth.of(year,month);
        soNgayTrongThang = yearMonth.lengthOfMonth();

        soNgayCong = chamCongs.size();
        System.out.println(soNgayCong);

        LuongNhanVien existingLuongNhanVien  = luongNhanVienRepository.findByMaNhanVienAndThangAndNam(dto.getMaNhanVien(), dto.getThang(), dto.getNam());
        if (existingLuongNhanVien != null){
            luong = taiKhoan.getLuong();
            existingLuongNhanVien.setLuong(tinhLuongNhanVien(luong,soNgayTrongThang,soNgayCong));
            existingLuongNhanVien.setNgayNghi(soNgayTrongThang - soNgayCong);
            existingLuongNhanVien.setNgayDiLam(soNgayCong);
            luongNhanVienRepository.save(existingLuongNhanVien);
            return existingLuongNhanVien;
        }else {
            LuongNhanVien luongNhanVien = new LuongNhanVien();
            luong = taiKhoan.getLuong();
            luongNhanVien.setLuong(tinhLuongNhanVien(luong, soNgayTrongThang, soNgayCong));
            luongNhanVien.setThang(dto.getThang());
            luongNhanVien.setNam(dto.getNam());
            luongNhanVien.setNgayNghi(soNgayTrongThang - soNgayCong);
            luongNhanVien.setNgayDiLam(soNgayCong);
            luongNhanVien.setMaNhanVien(dto.getMaNhanVien());
            luongNhanVien.setTenNhanVien(taiKhoan.getName());
            luongNhanVienRepository.save(luongNhanVien);
            return luongNhanVien;
        }


    }
//    @Override
//    public LuongNhanVien tinhLuong(LuongNhanVienDTO dto) {
//        String DateFormatter = dto.getThang()+"/"+dto.getNam();
//        int month = Integer.parseInt(dto.getThang());
//        int year = Integer.parseInt(dto.getNam());
//        int soNgayCong;
//        int soNgayTrongThang;
//        double luong = 0;
//
//        YearMonth yearMonth = YearMonth.of(year,month);
//        soNgayTrongThang = yearMonth.lengthOfMonth();
//
//        List<ChamCong> chamCongList = chamCongRepository.findByMaNhanVienAndNgayChamCongLike(dto.getMaNhanVien(),DateFormatter.trim());
//        soNgayCong = chamCongList.size();
//        System.out.println(soNgayCong);
//        TaiKhoan taiKhoan = taiKhoanRepository.findByMaNhanVien(dto.getMaNhanVien());
//        if (taiKhoan == null) {
//            throw new NotFoundException(String.format("Tài khoản có maNhanVien %s không tồn tại", dto.getMaNhanVien()));
//        }
//
//        LuongNhanVien existingLuongNhanVien  = luongNhanVienRepository.findByMaNhanVienAndThangAndNam(dto.getMaNhanVien(), dto.getThang(), dto.getNam());
//        if (existingLuongNhanVien != null){
//            luong = taiKhoan.getLuong();
//            existingLuongNhanVien.setLuong(tinhLuongNhanVien(luong,soNgayTrongThang,soNgayCong));
//            existingLuongNhanVien.setNgayNghi(soNgayTrongThang - soNgayCong);
//            existingLuongNhanVien.setNgayDiLam(soNgayCong);
//            luongNhanVienRepository.save(existingLuongNhanVien);
//            return existingLuongNhanVien;
//        }else {
//            LuongNhanVien luongNhanVien = new LuongNhanVien();
//            luong = taiKhoan.getLuong();
//            luongNhanVien.setLuong(tinhLuongNhanVien(luong, soNgayTrongThang, soNgayCong));
//            luongNhanVien.setThang(dto.getThang());
//            luongNhanVien.setNam(dto.getNam());
//            luongNhanVien.setNgayNghi(soNgayTrongThang - soNgayCong);
//            luongNhanVien.setNgayDiLam(soNgayCong);
//            luongNhanVien.setMaNhanVien(dto.getMaNhanVien());
//            luongNhanVien.setTenNhanVien(taiKhoan.getName());
//            luongNhanVienRepository.save(luongNhanVien);
//            return luongNhanVien;
//        }
//
//
//    }


}
