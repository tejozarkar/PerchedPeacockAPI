package com.perchedpeacock.ParkingLot.controller;

import com.perchedpeacock.ParkingLot.model.Booking;
import com.perchedpeacock.ParkingLot.model.BookingResponse;
import com.perchedpeacock.ParkingLot.model.HttpResponse;
import com.perchedpeacock.ParkingLot.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping()
    public ResponseEntity<List<BookingResponse>> getBookings(){
        List<BookingResponse> bookings = bookingService.getBookings();
        return ResponseEntity.status(200).body(bookings);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createBooking(@RequestBody Booking booking){
        bookingService.createBooking(booking);
        HttpResponse response = new HttpResponse();
        response.setMessage("Booking created");
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Booking> checkout(@RequestBody Booking booking){
        Booking updatedBooking = bookingService.checkout(booking);
        return ResponseEntity.status(200).body(updatedBooking);
    }

    @PostMapping("/payment")
    public String payment(@RequestBody Booking booking){
        bookingService.payment(booking);
        return "Checkout";
    }
}
