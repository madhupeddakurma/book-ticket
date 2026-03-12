package com.go.BookMyTicket.service;

import java.util.List;

import com.go.BookMyTicket.Entity.BookSeat;
import com.go.BookMyTicket.dto.BookingRequest;
import com.go.BookMyTicket.dto.BookingResponse;
import com.go.BookMyTicket.dto.DtoRequest;

public interface TicketService {
	
	void initializeSeats(int totalSeats);

	List<DtoRequest> getAllSeats();

	List<DtoRequest> getBookedSeats();

	BookingResponse bookSeats(BookingRequest bookingRequest);

}
