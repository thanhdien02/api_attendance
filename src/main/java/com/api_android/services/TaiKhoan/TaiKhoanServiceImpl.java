package com.api_android.services.TaiKhoan;

import com.api_android.dtos.TaiKhoanDto;
import com.api_android.entities.TaiKhoan;
import com.api_android.exceptions.InvalidException;
import com.api_android.exceptions.NotFoundException;
import com.api_android.repositories.TaiKhoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.Principal;
import java.util.List;

@Slf4j
@Service
public class TaiKhoanServiceImpl implements TaiKhoanService{
    private final TaiKhoanRepository taiKhoanRepository;

    public TaiKhoanServiceImpl(TaiKhoanRepository taiKhoanRepository) {
        this.taiKhoanRepository = taiKhoanRepository;
    }

    @Override
    public TaiKhoan getTaiKhoan(String id) {
        return taiKhoanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Tài khoản có id %s không tồn tại", id)));
    }

    @Override
    public List<TaiKhoan> getAll() {
        return taiKhoanRepository.findAll();
    }


    @Override
    public TaiKhoan create(TaiKhoanDto dto, Principal principal) {
        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên tài khoản không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getEmail())) {
            throw new InvalidException("Email không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getPassword())) {
            throw new InvalidException("Mật khẩu không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getDienThoai())) {
            throw new InvalidException("Điện thoại không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getMaNhanVien())) {
            throw new InvalidException("Mã nhân viên không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getRoles())) {
            throw new InvalidException("Vai trò tài khoản không được bỏ trống");
        }
        if (taiKhoanRepository.kiemTraEmail(dto.getEmail().trim())) {
            throw new InvalidException(String.format("Email %s đã tồn tại",
                    dto.getEmail()));
        }
        if (taiKhoanRepository.kiemTraMaNhanVien(dto.getMaNhanVien().trim())) {
            throw new InvalidException(String.format("Mã nhân viên %s đã tồn tại",
                    dto.getMaNhanVien()));
        }
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setName(dto.getName().trim());
        taiKhoan.setMaNhanVien(dto.getMaNhanVien().trim());
        taiKhoan.setEmail(dto.getEmail().trim());
        taiKhoan.setPassword(dto.getPassword());
        taiKhoan.setDienThoai(dto.getDienThoai().trim());
        taiKhoan.setRoles(dto.getRoles());
        taiKhoanRepository.save(taiKhoan);
        return taiKhoan;
    }

    @Override
    public TaiKhoan update(String id, TaiKhoanDto dto) {
        TaiKhoan taiKhoan = getTaiKhoan(id);
        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên tài khoản không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getEmail())) {
            throw new InvalidException("Email không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getPassword())) {
            throw new InvalidException("Mật khẩu không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getDienThoai())) {
            throw new InvalidException("Điện thoại không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getRoles())) {
            throw new InvalidException("Vai trò tài khoản không được bỏ trống");
        }
        if (!taiKhoan.getEmail().equalsIgnoreCase(dto.getEmail().trim())
                && taiKhoanRepository.kiemTraEmail(dto.getEmail().trim())){
            throw new InvalidException(String.format("Email %s đã tồn tại",
                    dto.getEmail()));
        }
        taiKhoan.setName(dto.getName().trim());
        taiKhoan.setEmail(dto.getEmail().trim());
        taiKhoan.setLuong(dto.getLuong());
        taiKhoan.setPassword(dto.getPassword());
        taiKhoan.setDienThoai(dto.getDienThoai().trim());
        taiKhoan.setRoles(dto.getRoles());

        taiKhoanRepository.save(taiKhoan);
        return taiKhoan;
    }

    @Override
    public TaiKhoan delete(String id) {
        TaiKhoan taiKhoan = getTaiKhoan(id);
        taiKhoanRepository.delete(taiKhoan);
        return taiKhoan;
    }
}
