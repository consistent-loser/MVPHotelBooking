package com.HotelBooking.MVPHotelBooking.Service;

import com.HotelBooking.MVPHotelBooking.BookingStatus;
import com.HotelBooking.MVPHotelBooking.DTO.ReservationDTO;
import com.HotelBooking.MVPHotelBooking.Entity.Building;
import com.HotelBooking.MVPHotelBooking.Entity.Reservation;
import com.HotelBooking.MVPHotelBooking.Exception.MVPException;
import com.HotelBooking.MVPHotelBooking.Repository.HotelRepository;
import com.HotelBooking.MVPHotelBooking.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private HotelRepository hotelRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository,HotelRepository hotelRepository){
        this.reservationRepository = reservationRepository;
        this.hotelRepository = hotelRepository;
    }

    public void bookHotel1(ReservationDTO reservationDTO){
        ReservationDTO t2 = new ReservationDTO();
        t2.setDate(reservationDTO.getDate());
        t2.setBuildingId(reservationDTO.getBuildingId());
        t2.setUserId("65ba65b4504777e7cf493dde");
        Thread thread1 = new Thread(() -> this.bookHotel(reservationDTO));
        Thread thread2 = new Thread(() -> this.bookHotel(t2));

        thread1.start();
        thread2.start();
    }
    @Transactional
    public Reservation bookHotel(ReservationDTO reservation){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate checkinDate = LocalDate.parse(reservation.getDate(),formatter);
        //fetch all reservations for the requested hotel for the requested day
        List<Reservation> reservations = reservationRepository.findAllByDateAndHotel(reservation.getBuildingId(),checkinDate);
        Reservation reservationObject = new Reservation();
//        fetch the hotel details
        Optional<Building> hotelDetails = hotelRepository.findById(reservation.getBuildingId());

        if(hotelDetails.isPresent() && reservations!=null){
            int availableRooms = hotelDetails.get().getNumberOfRooms() - reservations.size();

            if(availableRooms >0){
                //allot a room
                String roomId = selectFreeRoom(reservation.getBuildingId(),checkinDate);

                //set the reservation details
                reservationObject.setRoomId(roomId);
                reservationObject.setBuildingId(reservation.getBuildingId());
                reservationObject.setUserId(reservation.getUserId());
                reservationObject.setDate(checkinDate);
                reservationObject.setStatus(BookingStatus.SCHEDULED.toString());
                return reservationRepository.save(reservationObject);
            }else{
                throw new MVPException("All rooms occupied");
            }
        }else{
            throw new MVPException("Invalid hotel details");
        }

    }

    String selectFreeRoom(String buildingId,LocalDate date){

        List<Reservation> reservationList = reservationRepository.findAllByDateAndHotel(buildingId,date);
        Building hotel = hotelRepository.findById(buildingId).get();
        HashMap<String,Integer> roomOccupied= new HashMap<>();

        for(Reservation itr : reservationList){
            roomOccupied.put(itr.getRoomId(),1);
        }

        List<String> allRoomIds = hotel.getRooms();

        for(int i=0;i<allRoomIds.size();i++){
            if(!roomOccupied.containsKey(allRoomIds.get(i))){
                return allRoomIds.get(i);
            }
        }

        return null;
    }

    public List<Reservation> getReservationsByUserId(String userId){
        List<Reservation> reservations = reservationRepository.findByUserId(userId);

        return reservations;
    }

    public Reservation cancelReservation(String reservationId){
        Reservation reservation = reservationRepository.findById(reservationId).get();
        reservation.setStatus(BookingStatus.CANCELLED.toString());

        Reservation updatedReservation = reservationRepository.save(reservation);
        return updatedReservation;
    }

}
