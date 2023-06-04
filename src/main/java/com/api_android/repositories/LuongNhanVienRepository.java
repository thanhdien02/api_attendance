package com.api_android.repositories;

import com.api_android.entities.LuongNhanVien;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface LuongNhanVienRepository extends MongoRepository<LuongNhanVien,String> {
    @Query("{ 'maNhanVien': ?0, 'thang': ?1, 'nam': ?2 }")
    LuongNhanVien findByMaNhanVienAndThangAndNam(String maNhanVien, String thang, String nam);
}
