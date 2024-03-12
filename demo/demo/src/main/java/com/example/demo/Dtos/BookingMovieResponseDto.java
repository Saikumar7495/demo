package com.example.demo.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookingMovieResponseDto {
    private Long BookingId;
    private int amount;
    private ResponseStatus responseStatus;
    private String responseMessage;
}
