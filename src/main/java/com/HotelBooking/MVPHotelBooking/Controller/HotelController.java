package com.HotelBooking.MVPHotelBooking.Controller;

import com.HotelBooking.MVPHotelBooking.Entity.Building;
import com.HotelBooking.MVPHotelBooking.Entity.Room;
import com.HotelBooking.MVPHotelBooking.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HotelController {

//    helper functions to set up data. not in the project scope

    HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService){
        this.hotelService = hotelService;
    }
    @PostMapping("/hotel")
    public void addHotel(@RequestBody Building hotel){
        hotelService.addHotel(hotel);
    }

    @PostMapping("/hotel/room")
    public void addRoom(@RequestBody Room room){
        hotelService.addRoom(room);
    }
}
