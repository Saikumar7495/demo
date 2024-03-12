package com.example.demo.Services;

import com.example.demo.Dtos.BookingMovieRequestDto;
import com.example.demo.Repositories.BookingRepository;
import com.example.demo.Repositories.ShowRepository;
import com.example.demo.Repositories.ShowSeatRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.exceptions.InvalidShowException;
import com.example.demo.exceptions.InvalidUserException;
import com.example.demo.exceptions.SeatsUnavailableException;
import com.example.demo.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.*;


@Service
public class BookingService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private BookingRepository bookingRepository;
    public Booking bookMovie(BookingMovieRequestDto requestDto) throws InvalidUserException, InvalidShowException, SeatsUnavailableException {
       Long userid=requestDto.getUserId();
       Long showId=requestDto.getShowId();
       List<ShowSeat> showSeatId=requestDto.getShowSeatList();
       Optional<User> user=userRepository.findById(userid);
       if(user.isEmpty()){
           throw new InvalidUserException();
       }
       Optional<Show> show=showRepository.findById(showId);
       if(show.isEmpty()){
           throw new InvalidShowException();
       }
       List<ShowSeat> showSeatList=reserveSeats(showSeatId);
       return createBooking(user,show,showSeatList);
    }

    public Booking createBooking(Optional<User> user, Optional<Show> show, List<ShowSeat> showSeatList) {
        Booking booking=new Booking();
        booking.setBookingStatus(BookingStatus.WAITING);
        booking.setShowSeatList(showSeatList);
        booking.setPaymentList(new ArrayList<>());
        booking.setAmount(calculatePrice(showSeatList));
        booking.setUser(user.get());
        booking.setShow(show.get());
        return bookingRepository.save(booking);
    }

    private int calculatePrice(List<ShowSeat> showSeatList) {
        return 0;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<ShowSeat> reserveSeats(List<ShowSeat> showSeatList) throws SeatsUnavailableException {
//        List<ShowSeat> seatList;
//        seatList = showSeatRepository.findAllById(showSeatId);
        for(ShowSeat seat:showSeatList){
            if(!(seat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE))|| (seat.getShowSeatStatus().equals(ShowSeatStatus.LOCKED) && Duration.between(new Date().toInstant(),seat.getLocketAt().toInstant()).toMinutes()<10)){
                throw new SeatsUnavailableException();

            }
        }

        for(ShowSeat seat:showSeatList){
            seat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            seat.setLocketAt(new Date());
        }
        return showSeatRepository.saveAll(showSeatList);
    }
}
