package com.HotelBooking.MVPHotelBooking.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "Building")
public class Building {

    @Id
    private String _id;
    private String name;
    private int numberOfRooms;
    private List<String> rooms;

    @Version
    @Transient
    private int version;
}
