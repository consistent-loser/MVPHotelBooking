package com.HotelBooking.MVPHotelBooking.Repository;

import com.HotelBooking.MVPHotelBooking.Entity.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Room,String> {
}
