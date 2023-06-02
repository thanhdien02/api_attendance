package com.api_android.controllers;

import com.api_android.dtos.ChamCongDto;
import com.api_android.dtos.LuongDto;
import com.api_android.entities.ChamCong;
import com.api_android.entities.Luong;
import com.api_android.services.Luong.LuongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/luong")
public class LuongController {
    @Autowired
    private final LuongService luongService;

    public LuongController(LuongService luongService) {
        this.luongService = luongService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Luong> getTaiKhoan(@PathVariable String id) {
        return new ResponseEntity<>(luongService.getLuong(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Luong>> getAll() {
        return new ResponseEntity<>(luongService.getAll(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Luong> create(@Valid @RequestBody LuongDto dto) {
        return new ResponseEntity<>(luongService.create(dto), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Luong> update(@PathVariable String id, @Valid @RequestBody LuongDto dto) {
        return new ResponseEntity<>(luongService.update(id, dto), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Luong> delete(@PathVariable String id) {
        return new ResponseEntity<>(luongService.delete(id), HttpStatus.OK);
    }

}
