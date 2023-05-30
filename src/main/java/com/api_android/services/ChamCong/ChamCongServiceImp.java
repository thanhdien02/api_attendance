package com.api_android.services.ChamCong;

import com.api_android.dtos.ChamCongDto;
import com.api_android.entities.ChamCong;
import com.api_android.exceptions.InvalidException;
import com.api_android.exceptions.NotFoundException;
import com.api_android.repositories.ChamCongRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Service
public class ChamCongServiceImp implements ChamCongService{

    private final ChamCongRepository chamCongRepository;

    public ChamCongServiceImp(ChamCongRepository chamCongRepository) {
        this.chamCongRepository = chamCongRepository;
    }

    @Override
    public ChamCong getChamCong(String id) {
        return chamCongRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Ngày chấm công có id %s không tồn tại", id)));
    }

    @Override
    public List<ChamCong> getAll() {
        return chamCongRepository.findAll();
    }

    @Override
    public ChamCong create(ChamCongDto dto) {
        if (ObjectUtils.isEmpty(dto.getTen())) {
            throw new InvalidException("Tên nhân viên không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getMaNhanVien())) {
            throw new InvalidException("Mã nhân viên không được bỏ trống");
        }
//        if (ObjectUtils.isEmpty(dto.getNgayChamCong())) {
//            throw new InvalidException("Ngày chấm công không được bỏ trống");
//        }
//
//        Kiem tra moi nhan vien moi ngay chi cham cong 1 lan ma thoi.

        String ngaychamcong = dto.getNgayChamCong();
        String[] arr = ngaychamcong.split("/");

        List<ChamCong> lsChamCong = getAll();

        for (int i = 0; i < lsChamCong.size(); i++)
        {
            String ngaychamcongdb = lsChamCong.get(i).getNgayChamCong();
            String[] arrdb = ngaychamcongdb.split("/");
            if(arr[0].equals(arrdb[0]) && arr[1].equals(arrdb[1]))
            {
                throw new InvalidException("Mỗi nhân viên chỉ được chấm công 1 lần mỗi ngày");
            }
        }
        ChamCong chamCong = new ChamCong();
        chamCong.setTen(dto.getTen().trim());
        chamCong.setNgayChamCong(dto.getNgayChamCong());
        chamCong.setMaNhanVien(dto.getMaNhanVien().trim());

        chamCongRepository.save(chamCong);
        return chamCong;
    }

    @Override
    public ChamCong update(String id, ChamCongDto dto) {
        ChamCong chamCong = getChamCong(id);
        if (ObjectUtils.isEmpty(dto.getTen())) {
            throw new InvalidException("Tên nhân viên không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getMaNhanVien())) {
            throw new InvalidException("Mã nhân viên không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getNgayChamCong())) {
            throw new InvalidException("Ngày chấm công không được bỏ trống");
        }

        chamCong.setTen(dto.getTen().trim());
        chamCong.setNgayChamCong(dto.getNgayChamCong());
        chamCong.setMaNhanVien(dto.getMaNhanVien().trim());

        chamCongRepository.save(chamCong);
        return chamCong;
    }

    @Override
    public ChamCong delete(String id) {
        ChamCong chamCong = getChamCong(id);
        chamCongRepository.delete(chamCong);
        return chamCong;
    }
}
