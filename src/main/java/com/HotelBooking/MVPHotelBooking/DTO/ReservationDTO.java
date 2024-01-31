package com.HotelBooking.MVPHotelBooking.DTO;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@EntityScan
@Data
public class ReservationDTO {
    String buildingId;
    String date;
    String userId;

}
