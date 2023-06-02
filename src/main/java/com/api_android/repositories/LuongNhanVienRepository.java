package com.api_android.repositories;

import com.api_android.entities.LuongNhanVien;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface LuongNhanVienRepository extends MongoRepository<LuongNhanVien,String> {

}
