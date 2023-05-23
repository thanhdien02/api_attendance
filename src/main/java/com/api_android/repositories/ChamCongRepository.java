package com.api_android.repositories;

import com.api_android.entities.ChamCong;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChamCongRepository extends MongoRepository<ChamCong, String> {


}
