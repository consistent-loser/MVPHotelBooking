package com.HotelBooking.MVPHotelBooking.Service;

import com.HotelBooking.MVPHotelBooking.Entity.Building;
import com.HotelBooking.MVPHotelBooking.Entity.Room;
import com.HotelBooking.MVPHotelBooking.Exception.MVPException;
import com.HotelBooking.MVPHotelBooking.Repository.HotelRepository;
import com.HotelBooking.MVPHotelBooking.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private HotelRepository hotelRepository;
    private RoomRepository roomRepository;
    @Autowired
    public HotelService(HotelRepository hotelRepository,RoomRepository roomRepository){
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }
    public void addHotel(Building hotel){
        System.out.println(hotel);
        hotelRepository.save(hotel);
    }

    @Transactional
    public void addRoom(Room room){

        String hotelId = room.getBuildingId();
        Optional<Building> hotel = hotelRepository.findById(hotelId);
        List<String> roomList = new ArrayList<>();

        Room savedRoom = roomRepository.save(room);

        if(hotel.isPresent()){
            roomList = hotel.get().getRooms();

            if(roomList != null){
                roomList.add(savedRoom.get_id());
            }else{
                roomList = new ArrayList<>();
                roomList.add(savedRoom.get_id());
            }
            hotel.get().setRooms(roomList);
            hotelRepository.save(hotel.get());
        }
    }

    @Cacheable
    public Building getHotelById(String id){
        Building hotel = hotelRepository.findById(id).get();

        if(hotel == null){
            throw new MVPException("No hotel found");
        }
        return hotel;
    }
}
