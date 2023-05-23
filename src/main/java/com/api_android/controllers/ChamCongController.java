package com.api_android.controllers;

import com.api_android.dtos.ChamCongDto;
import com.api_android.dtos.TaiKhoanDto;
import com.api_android.entities.ChamCong;
import com.api_android.entities.TaiKhoan;
import com.api_android.services.ChamCong.ChamCongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/rest/cham-cong")
public class ChamCongController {
    @Autowired
    private final ChamCongService chamCongService;

    public ChamCongController(ChamCongService chamCongService) {
        this.chamCongService = chamCongService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamCong> getTaiKhoan(@PathVariable String id) {
        return new ResponseEntity<>(chamCongService.getChamCong(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ChamCong>> getAll() {
        return new ResponseEntity<>(chamCongService.getAll(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ChamCong> create(@Valid @RequestBody ChamCongDto dto) {
        return new ResponseEntity<>(chamCongService.create(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ChamCong> update(@PathVariable String id, @Valid @RequestBody ChamCongDto dto) {
        return new ResponseEntity<>(chamCongService.update(id, dto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ChamCong> delete(@PathVariable String id) {
        return new ResponseEntity<>(chamCongService.delete(id), HttpStatus.OK);
    }
}
