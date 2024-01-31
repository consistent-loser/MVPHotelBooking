package com.HotelBooking.MVPHotelBooking.Repository;

import com.HotelBooking.MVPHotelBooking.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
}
