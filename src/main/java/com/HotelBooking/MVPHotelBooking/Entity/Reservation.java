package com.HotelBooking.MVPHotelBooking.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Data
@Document(collection = "Reservation")
public class Reservation {

    @Id
    private String id;
    private String buildingId;
    private String roomId;
    private LocalDate date;
    private String userId;
    private String status;

    @Version
    @Transient
    private Long version;
}
