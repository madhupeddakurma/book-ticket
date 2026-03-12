package com.go.BookMyTicket.service;

import org.springframework.beans.BeanUtils;

import com.go.BookMyTicket.Entity.BookSeat;
import com.go.BookMyTicket.dto.DtoRequest;

public interface TicketBookingMapper {
	
	public static BookSeat mapper1(DtoRequest dtoRequest) {
		BookSeat bookSeat = new BookSeat();
		BeanUtils.copyProperties(dtoRequest, bookSeat);
		return bookSeat;
	}
	public static DtoRequest mapper2(BookSeat bookSeat) {
	    return new DtoRequest(
	        bookSeat.getId(),
	        bookSeat.isBooked(),
	        bookSeat.getBookedBy()
	    );
	}



}
