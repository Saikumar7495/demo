package com.example.demo.Controllers;

import com.example.demo.Dtos.BookingMovieRequestDto;
import com.example.demo.Dtos.BookingMovieResponseDto;
import com.example.demo.Dtos.ResponseStatus;
import com.example.demo.Services.BookingService;
import com.example.demo.exceptions.InvalidShowException;
import com.example.demo.exceptions.InvalidUserException;
import com.example.demo.exceptions.SeatsUnavailableException;
import com.example.demo.models.Booking;
import com.example.demo.models.BookingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    public BookingMovieResponseDto bookMovie(BookingMovieRequestDto requestDto){
        try {
            Booking booking=bookingService.bookMovie(requestDto);
            return new BookingMovieResponseDto(booking.getId(),booking.getAmount(), ResponseStatus.SUCCESS,"Success");
        } catch (InvalidUserException e) {
            return new BookingMovieResponseDto(null,0,ResponseStatus.FAILURE,"Invalid User");
        } catch (InvalidShowException e) {
            return new BookingMovieResponseDto(null,0,ResponseStatus.FAILURE,"Invalid show");
        } catch (SeatsUnavailableException e) {
            return new BookingMovieResponseDto(null,0,ResponseStatus.FAILURE,"Seats unavailable");
        }

//        return null;
    }

}
