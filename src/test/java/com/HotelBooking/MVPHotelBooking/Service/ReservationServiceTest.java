package com.HotelBooking.MVPHotelBooking.Service;

import com.HotelBooking.MVPHotelBooking.BookingStatus;
import com.HotelBooking.MVPHotelBooking.DTO.ReservationDTO;
import com.HotelBooking.MVPHotelBooking.Entity.Building;
import com.HotelBooking.MVPHotelBooking.Entity.Reservation;
import com.HotelBooking.MVPHotelBooking.Repository.HotelRepository;
import com.HotelBooking.MVPHotelBooking.Repository.ReservationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private ReservationService reservationService;

     private static Reservation reservatonObject;

     private static Building hotelObject;


    @BeforeAll
     private static void setReservationObject(){
        reservatonObject = new Reservation();
        //set mock data for reservation
        reservatonObject.setUserId("65b9c5e2fc55124eb4874a30");
        reservatonObject.setRoomId("65b9c316966c9162c4772db9");
        reservatonObject.setDate(LocalDate.now());
        reservatonObject.setBuildingId("65b9bef4aef4c66866e5d1c5");
        reservatonObject.setStatus(BookingStatus.SCHEDULED.toString());

        //set mock data for hotel
        hotelObject = new Building();
        hotelObject.set_id("65b9bef4aef4c66866e5d1c5");
        hotelObject.setName("TownHouse");
        hotelObject.setRooms(List.of("65b9c316966c9162c4772db9","65b9c316966c9162c4772db9"));
        hotelObject.setNumberOfRooms(2);
    }

    @Test
    public void testSaveReservation(){

        //add mock criteria
        Mockito.when(reservationRepository.save(Mockito.any(Reservation.class))).thenReturn(reservatonObject);
        Mockito.when(reservationRepository.findAllByDateAndHotel(Mockito.any(),Mockito.any())).thenReturn(List.of(reservatonObject));
        Mockito.when(hotelRepository.findById(Mockito.any())).thenReturn(Optional.of(hotelObject));

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setUserId("65b9c5e2fc55124eb4874a30");
        reservationDTO.setDate(LocalDate.now().toString());
        reservationDTO.setBuildingId("65b9bef4aef4c66866e5d1c5");

        Reservation reservationResult = reservationService.bookHotel(reservationDTO);

        assertEquals(reservationResult.getUserId(),"65b9c5e2fc55124eb4874a30");
        assertEquals(reservationResult.getStatus(), BookingStatus.SCHEDULED.toString());

    }

    @Test
    public void cancelReservation(){
        Mockito.when(reservationRepository.findById(Mockito.any())).thenReturn(Optional.of(reservatonObject));

        reservatonObject.setStatus(BookingStatus.CANCELLED.toString());

        Mockito.when(reservationRepository.save(Mockito.any(Reservation.class))).thenReturn(reservatonObject);

        Reservation updatedReservation = reservationService.cancelReservation("65b9c5e2fc55124eb4874a30");

        assertEquals(updatedReservation.getStatus(),BookingStatus.CANCELLED.toString());
    }
}
