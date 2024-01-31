package com.HotelBooking.MVPHotelBooking.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Room")
public class Room {

    @Id
    private String _id;
    private String roomType;
    private String buildingId;
    private String price;

    @Version
    @Transient
    private int version;
}
