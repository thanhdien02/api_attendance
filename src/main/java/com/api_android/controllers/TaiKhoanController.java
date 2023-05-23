package com.api_android.controllers;

import com.api_android.dtos.TaiKhoanDto;
import com.api_android.entities.TaiKhoan;
import com.api_android.services.TaiKhoan.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/rest/tai-khoan")
public class TaiKhoanController {

    @Autowired
    private final TaiKhoanService taiKhoanService;

    public TaiKhoanController(TaiKhoanService taiKhoanService) {
        this.taiKhoanService = taiKhoanService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaiKhoan> getTaiKhoan(@PathVariable String id) {
        return new ResponseEntity<>(taiKhoanService.getTaiKhoan(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TaiKhoan>> getAll() {
        return new ResponseEntity<>(taiKhoanService.getAll(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TaiKhoan> create(@Valid @RequestBody TaiKhoanDto dto, Principal principal) {
        return new ResponseEntity<>(taiKhoanService.create(dto, principal), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TaiKhoan> update(@PathVariable String id, @Valid @RequestBody TaiKhoanDto dto) {
        return new ResponseEntity<>(taiKhoanService.update(id, dto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<TaiKhoan> delete(@PathVariable String id) {
        return new ResponseEntity<>(taiKhoanService.delete(id), HttpStatus.OK);
    }
}
