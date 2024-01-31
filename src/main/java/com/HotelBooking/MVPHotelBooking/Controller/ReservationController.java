package com.HotelBooking.MVPHotelBooking.Controller;

import com.HotelBooking.MVPHotelBooking.DTO.ReservationDTO;
import com.HotelBooking.MVPHotelBooking.Entity.Reservation;
import com.HotelBooking.MVPHotelBooking.Exception.MVPException;
import com.HotelBooking.MVPHotelBooking.Service.ReservationService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping("")
    public ResponseEntity<Reservation> bookHotel(@RequestBody ReservationDTO reservation){
        try{
            Reservation savedReservation = reservationService.bookHotel(reservation);
            return new ResponseEntity<Reservation>(savedReservation, HttpStatus.CREATED);
        }catch(MVPException ex){
            throw new MVPException(ex.getMessage());
        }

    }

    @PutMapping("")
    public ResponseEntity<Reservation> cancelBooking(String bookingId){
        try{
            Reservation updatedReservation = reservationService.cancelReservation(bookingId);
            return new ResponseEntity<Reservation>(updatedReservation,HttpStatus.OK);
        }catch(MVPException ex){
            throw new MVPException(ex.getMessage());
        }
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<Reservation>> getReservationList(@PathVariable String userId){
        List<Reservation> reservations =reservationService.getReservationsByUserId(userId);

        return new ResponseEntity<List<Reservation>>(reservations,HttpStatus.OK);
    }
}
