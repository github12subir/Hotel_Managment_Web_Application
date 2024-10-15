package com.AirBnb.service.Interface;

import com.AirBnb.entity.AppUser;
import com.AirBnb.payload.BookingDto;

import java.util.List;

public interface BookingService {

    BookingDto createBooking(BookingDto bookingDto, Long roomId, AppUser user);

    void cencelBooking(String bookingId);

    BookingDto updateBooking(BookingDto bookingDto, Long roomId, String bookingId);

    BookingDto showBookingById(String bookingId);

    List<BookingDto> showBookingsByUser(Long userId);

    void cancelAllBookingByUser(Long userId);
}
