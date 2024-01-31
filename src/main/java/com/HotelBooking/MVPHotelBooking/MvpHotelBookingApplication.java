package com.HotelBooking.MVPHotelBooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MvpHotelBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvpHotelBookingApplication.class, args);
	}

}
