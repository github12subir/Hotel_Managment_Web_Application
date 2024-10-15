package com.AirBnb.controler;

import com.AirBnb.entity.AppUser;
import com.AirBnb.payload.BookingDto;
import com.AirBnb.service.Interface.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airbnb/booking")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    //Create Booking
    //http://localhost:8080/api/airbnb/booking/create/{roomId}
    @PostMapping("/create/{roomId}")
    public ResponseEntity<BookingDto> createBooking(
            @RequestBody BookingDto bookingDto,
            @PathVariable Long roomId,
            @AuthenticationPrincipal AppUser user
            ){

        BookingDto dto = bookingService.createBooking(bookingDto, roomId,user);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    //Edit the Booking
    //http://localhost:8080/api/airbnb/booking/edit/{roomId}/{bookingId}
    @PutMapping("/edit/{roomId}/{bookingId}")
    public ResponseEntity<BookingDto> EditBooking(
            @RequestBody BookingDto bookingDto,
            @PathVariable Long roomId,
            @PathVariable String bookingId
    ){
        BookingDto dto = bookingService.updateBooking(bookingDto,roomId, bookingId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    //Show the Booking By The Booking Id
    //http://localhost:8080/api/airbnb/booking/showBookingById/{bookingId}
    @GetMapping("/showBookingById/{bookingId}")
    public ResponseEntity<BookingDto> showBookingDetails(
            @PathVariable String bookingId
    ){
        BookingDto dto = bookingService.showBookingById(bookingId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    //Show All the Booking Details By User
    //http://localhost:8080/api/airbnb/booking/showAllTheBookingsBuUser/{userId}
    @GetMapping("/showAllTheBookingsBuUser/{userId}")
    public ResponseEntity<List<BookingDto>> showBookingsByUser(
            @PathVariable Long userId
    ){
        List<BookingDto> dtos = bookingService.showBookingsByUser(userId);
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }


    //Cancel the Booking By Booking id
    //http://localhost:8080/api/airbnb/booking/cancel/{bookingId}
    @DeleteMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(
            @PathVariable String bookingId
    ){
        bookingService.cencelBooking(bookingId);
        return new ResponseEntity<>("Your Booking Canceled!",HttpStatus.OK);
    }


    //Cancel All the Bookings By User
    //http://localhost:8080/api/airbnb/booking/cancelAllBookingByUser/{userId}
    @DeleteMapping("/cancelAllBookingByUser/{userId}")
    public ResponseEntity<String> cancelAllBookings(
            @PathVariable Long userId
    ){
        bookingService.cancelAllBookingByUser(userId);
        return  new ResponseEntity<>("Successfully Canceled your All Bookings!",HttpStatus.OK);
    }

}
