package com.HotelBooking.MVPHotelBooking.Repository;

import com.HotelBooking.MVPHotelBooking.Entity.Building;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends MongoRepository<Building,String> {
}
