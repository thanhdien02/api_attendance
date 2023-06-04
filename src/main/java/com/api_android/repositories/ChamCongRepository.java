package com.api_android.repositories;

import com.api_android.entities.ChamCong;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public interface ChamCongRepository extends MongoRepository<ChamCong, String> {
    List<ChamCong> findByMaNhanVien(String maNhanVien);
}
