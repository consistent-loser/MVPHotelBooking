package com.HotelBooking.MVPHotelBooking.Repository;

import com.HotelBooking.MVPHotelBooking.Entity.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

//@Repository
@EnableMongoRepositories
public interface ReservationRepository extends MongoRepository<Reservation, String> {

    @Query("{'buildingId': ?0, 'date': ?1, 'status':{$ne:'CANCELLED'}}")
    List<Reservation> findAllByDateAndHotel(String hotelId, LocalDate Date);

    List<Reservation> findByUserId(String userId);

}
