package com.api_android.services.Luong;

import com.api_android.dtos.LuongDto;
import com.api_android.entities.ChamCong;
import com.api_android.entities.Luong;
import com.api_android.exceptions.InvalidException;
import com.api_android.exceptions.NotFoundException;
import com.api_android.repositories.ChamCongRepository;
import com.api_android.repositories.LuongRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Service
public class LuongServiceImp implements LuongService{

    private final LuongRepository luongRepository;

    private final ChamCongRepository chamCongRepository;

    public LuongServiceImp(LuongRepository luongRepository, ChamCongRepository chamCongRepository) {
        this.luongRepository = luongRepository;
        this.chamCongRepository = chamCongRepository;
    }

    @Override
    public Luong getLuong(String id) {
        return luongRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Lương có id %s không tồn tại", id)));
    }

    @Override
    public Boolean getByMaThang(String manv, int thang, int nam) {
        List<Luong> lsluong = luongRepository.findAll();

        for(int i = 0 ; i < lsluong.size(); i++)
        {
            if(manv.equals(lsluong.get(i).getMaNhanVien()) && thang == lsluong.get(i).getThang() && nam == lsluong.get(i).getNam())
            {
                return true;
            }

        }
        return false;
    }

    @Override
    public List<Luong> getAll() {
        return luongRepository.findAll();
    }

    @Override
    public Luong create(LuongDto dto) {
        if (ObjectUtils.isEmpty(dto.getTenNhanVien())) {
            throw new InvalidException("Tên nhân viên không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getMaNhanVien())) {
            throw new InvalidException("Mã nhân viên không được bỏ trống");
        }

        List<ChamCong> lschamcong= chamCongRepository.findAll();
        int ngaylam = 0;
        if(lschamcong.size() > 0)
        {
            for (int i = 0; i < lschamcong.size(); i++)
            {
                String laythang = lschamcong.get(i).getNgayChamCong();
                System.out.println("Day a: " + laythang);
                String[] arr = laythang.split("/");

                String nam = arr[2].substring(0, 4);
                if(dto.getThang() == Integer.parseInt(arr[1]) && dto.getMaNhanVien().equals(lschamcong.get(i).getMaNhanVien()) && dto.getNam() == Integer.parseInt(nam))
                {
                    ngaylam++;
                }

            }
        }

        if(getByMaThang(dto.getMaNhanVien(), dto.getThang(), dto.getNam()))
        {
            throw new InvalidException("Nhân viên này đã tính lương trong tháng rồi");
        }
        Double tongluong = dto.getTongLuong() * ngaylam;


        Luong luong = new Luong();
        luong.setMaNhanVien(dto.getMaNhanVien().trim());
        luong.setTenNhanVien(dto.getTenNhanVien());
        luong.setTongLuong(tongluong);
        luong.setThang(dto.getThang());
        luong.setNam(dto.getNam());
        luong.setSoNgayLamTrongThang(ngaylam);

        luongRepository.save(luong);
        return luong;
    }


    @Override
    public Luong update(String id, LuongDto dto) {
        Luong luong = getLuong(id);
        if (ObjectUtils.isEmpty(dto.getTenNhanVien())) {
            throw new InvalidException("Tên nhân viên không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getMaNhanVien())) {
            throw new InvalidException("Mã nhân viên không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getSoNgayLamTrongThang())) {
            throw new InvalidException("Số ngày làm không được bỏ trống");
        }

        luong.setMaNhanVien(dto.getMaNhanVien().trim());
        luong.setTenNhanVien(dto.getTenNhanVien());
        luong.setTongLuong(dto.getTongLuong());
        luong.setThang(dto.getThang());
        luong.setNam(dto.getNam());
        luong.setSoNgayLamTrongThang(dto.getSoNgayLamTrongThang());

        luongRepository.save(luong);
        return luong;
    }

    @Override
    public Luong delete(String id) {
        Luong luong = getLuong(id);
        luongRepository.delete(luong);
        return luong;
    }
}
