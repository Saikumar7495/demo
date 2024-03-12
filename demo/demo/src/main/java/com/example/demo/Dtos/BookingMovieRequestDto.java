package com.example.demo.Dtos;

import com.example.demo.models.ShowSeat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
public class BookingMovieRequestDto {
    private Long userId;
    private Long showId;
    private List<ShowSeat> showSeatList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public List<ShowSeat> getShowSeatList() {
        return showSeatList;
    }

    public void setShowSeatList(List<ShowSeat> showSeatList) {
        this.showSeatList = showSeatList;
    }
}
