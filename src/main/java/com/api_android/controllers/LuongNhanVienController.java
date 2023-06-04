package com.api_android.controllers;

import com.api_android.dtos.LuongNhanVienDTO;
import com.api_android.entities.LuongNhanVien;
import com.api_android.services.LuongNhanVien.LuongNhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/luong-nhan-vien")
public class LuongNhanVienController {
    @Autowired
    LuongNhanVienService luongNhanVienService;

    @GetMapping
    public ResponseEntity<List<LuongNhanVien>> getAll(){
        return new ResponseEntity<>(luongNhanVienService.getALL(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LuongNhanVien> tinhLuong(@RequestBody LuongNhanVienDTO dto){
        return new ResponseEntity<>(luongNhanVienService.tinhLuong(dto), HttpStatus.OK);
    }
}
