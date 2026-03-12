package com.go.BookMyTicket.dto;

import java.util.List;

public record BookedSeatResponse(List<DtoRequest> bookedSeats, long totalSeatBooked) {} 


