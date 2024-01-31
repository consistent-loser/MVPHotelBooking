package com.HotelBooking.MVPHotelBooking.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class User {
    @Id
    private String _id;
    private String name;
    private String role;

    private String email;

    @Version
    @Transient
    private int version;
}
