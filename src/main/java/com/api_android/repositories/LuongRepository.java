package com.api_android.repositories;

import com.api_android.entities.Luong;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface LuongRepository extends MongoRepository<Luong, String> {


}
