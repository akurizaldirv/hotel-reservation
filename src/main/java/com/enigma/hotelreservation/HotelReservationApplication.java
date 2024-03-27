package com.enigma.hotelreservation;

import com.enigma.hotelreservation.util.FileUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelReservationApplication {

    public static void main(String[] args) {
        FileUtil.createUploadDirectory();
        SpringApplication.run(HotelReservationApplication.class, args);
    }

}
