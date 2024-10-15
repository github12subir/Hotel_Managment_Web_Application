package com.AirBnb.service.Impl;

import com.AirBnb.entity.AppUser;
import com.AirBnb.entity.Booking;
import com.AirBnb.entity.Room;
import com.AirBnb.exception.BookingNotFound;
import com.AirBnb.exception.RoomNotExist;
import com.AirBnb.payload.BookingDto;
import com.AirBnb.repository.AppUserRepository;
import com.AirBnb.repository.BookingRepository;
import com.AirBnb.repository.RoomRepository;
import com.AirBnb.service.Interface.BookingService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    private AppUserRepository userRepository;

    private RoomRepository roomRepository;
    public BookingServiceImpl(BookingRepository bookingRepository, AppUserRepository userRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    //Create a Booking
    @Override
    public BookingDto createBooking(BookingDto bookingDto, Long roomId, AppUser user) {
        //Generate a random id of String type
        String bookingId = UUID.randomUUID().toString();
        //set the Booking id into Booking Object
        bookingDto.setId(bookingId);

        Booking booking = mapToEntity(bookingDto);

        //check the room Exist or Not
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotExist("This Room is Not Exist or Not Available!"));



        //set the room in Booking object
        booking.setRoom(room);

        //set the User in Booking object
        booking.setAppUser(user);

        //calculate the Total Price
        Double totalPrice = calculateTotalPrice(bookingDto.getCheckIn(), bookingDto.getCheckOut(), room.getNightlyPrice());

        //set the Total Price in Booking Object
        booking.setTotalPrice(totalPrice);

        //save the Booking Object into Database
        Booking saveEntity = bookingRepository.save(booking);
        BookingDto saveDto = mapToDto(saveEntity);
        saveDto.setMessage("Booking Confirm!");
        return saveDto;
    }

    //Cancel the Booking or Delete
    @Override
    public void cencelBooking(String bookingId) {
        Booking booking = bookingRepository.findByBookingId(bookingId).orElseThrow(() -> new BookingNotFound("Booking Not Found!"));
        bookingRepository.delete(booking);
    }

    //Edit the Booking
    @Override
    public BookingDto updateBooking(BookingDto bookingDto, Long roomId, String bookingId) {
        Booking booking = bookingRepository.findByBookingId(bookingId).orElseThrow(() -> new BookingNotFound("Booking Not Found!"));

        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotExist("Room Not Exist!"));

        booking.setRoomNumber(bookingDto.getRoomNumber());
        booking.setCheckIn(bookingDto.getCheckIn());
        booking.setCheckOut(bookingDto.getCheckOut());
        booking.setTotalNoOfGuest(bookingDto.getTotalNoOfGuest());
        booking.setName(bookingDto.getName());
        booking.setPhNumber(bookingDto.getPhNumber());

        //Edit & set the Total Price into Booking Object
        Double totalPrice = calculateTotalPrice(bookingDto.getCheckIn(), bookingDto.getCheckOut(), room.getNightlyPrice());
        booking.setTotalPrice(totalPrice);

        Booking saveEntity = bookingRepository.save(booking);
        BookingDto saveDto = mapToDto(saveEntity);
        saveDto.setMessage("Successfully, Your Booking is Edited!");
        return saveDto;
    }

    //Show the Booking Details By Booking ID
    @Override
    public BookingDto showBookingById(String bookingId) {

        Booking booking = bookingRepository.findByBookingId(bookingId).orElseThrow(() -> new BookingNotFound("Booking Not Exist!"));
        BookingDto bookingDto = mapToDto(booking);
        return bookingDto;
    }

    @Override
    public List<BookingDto> showBookingsByUser(Long userId) {
        List<Booking> bookingsEntity = bookingRepository.findAllBookingsByUser(userId);

        if (bookingsEntity.isEmpty()){
            throw  new RuntimeException("There are no Bookings!");
        }

        List<BookingDto> bookingsDto = bookingsEntity.stream().map(booking -> mapToDto(booking)).collect(Collectors.toList());
        return bookingsDto;
    }

    @Override
    public void cancelAllBookingByUser(Long userId) {
        List<Booking> allBookingsByUser = bookingRepository.findAllBookingsByUser(userId);

        if (allBookingsByUser.isEmpty()){
            throw new RuntimeException("you have no Bookings!");
        }

        bookingRepository.deleteAll(allBookingsByUser);
    }

    //Map to Entity
    Booking mapToEntity(BookingDto dto){

        Booking entity=new Booking();
        entity.setId(dto.getId());
        entity.setRoomNumber(dto.getRoomNumber());
        entity.setCheckIn(dto.getCheckIn());
        entity.setCheckOut(dto.getCheckOut());
        //entity.setTotalPrice(dto.getTotalPrice());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhNumber(dto.getPhNumber());
        entity.setTotalNoOfGuest(dto.getTotalNoOfGuest());
        return entity;
    }

    //Map to Dto
    BookingDto mapToDto(Booking entity){

        BookingDto dto=new BookingDto();
        dto.setRoomNumber(entity.getRoomNumber());
        dto.setCheckIn(entity.getCheckIn());
        dto.setCheckOut(entity.getCheckOut());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setName(entity.getName());
       // dto.setEmail(entity.getEmail());
        dto.setPhNumber(entity.getPhNumber());
        dto.setTotalNoOfGuest(entity.getTotalNoOfGuest());
        return dto;
    }

    //Calculate The Total Price
    public Double calculateTotalPrice(LocalDate checkIn, LocalDate checkOut, Double nightlyPrice){

        //checked the Booking Details is Valid or Not
        if (checkIn==null || checkOut==null || nightlyPrice==null){
            throw new IllegalArgumentException("Invalid Booking Details for Price calculation!");
        }

        //calculate the total days
        long days = Duration.between(checkIn.atStartOfDay(), checkOut.atStartOfDay()).toDays();
        //che
        if (days<1){
            throw new IllegalArgumentException("checkIn date must be after checkOut date!");
        }
        Double totalPrice=days*nightlyPrice;
        return totalPrice;

    }
}
