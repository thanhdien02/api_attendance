package com.api_android.repositories;

import com.api_android.entities.TaiKhoan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface TaiKhoanRepository extends MongoRepository<TaiKhoan, String> {
    @Query(value = "{'email': ?0}")
    Optional<TaiKhoan> getTaiKhoan(String email);

    @Query(value = "{'Email': ?0}", exists = true)
    boolean kiemTraEmail(String Email);

    @Query(value = "{'MaNhanVien': ?0}", exists = true)
    boolean kiemTraMaNhanVien(String MaNhanVien);

    @Query(value = "{'MaNhanVien': ?0}")
    TaiKhoan findByMaNhanVien(String MaNhanVien);
}
